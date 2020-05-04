import {Component, ElementRef, EventEmitter, Input, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import 'fabric';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Filter} from "../../models/filter";

declare let fabric;

@Component({
  selector: 'app-image-editor',
  templateUrl: './image-editor.component.html',
  styleUrls: ['./image-editor.component.css']
})
export class ImageEditorComponent implements OnInit {

  @Input()
  image: File;

  @Input()
  index: number;

  @Output()
  onSaveFile = new EventEmitter<any>();

  public maskURLs: string[] = [
    "/assets/masks/1.png",
    "/assets/masks/2.png",
    "/assets/masks/3.png",
    "/assets/masks/4.png",
    "/assets/masks/5.png",
    "/assets/masks/6.png",
    "/assets/masks/7.png",
    "/assets/masks/8.png",
    "/assets/masks/9.png",
    "/assets/masks/10.png",
    "/assets/masks/11.png"
  ];

  public filters: Filter[] = [
    {name: "Sepia", filterObj: new fabric.Image.filters.Sepia, isApplied: false},
    {name: "Grayscale", filterObj: new fabric.Image.filters.Grayscale, isApplied: false},
    {name: "Pixelate", filterObj: new fabric.Image.filters.Pixelate, isApplied: false},
    {name: "Invert", filterObj: new fabric.Image.filters.Invert, isApplied: false}
  ];

  @ViewChild('canvasElement', {read: undefined, static: false}) el: ElementRef;

  private canvas: any;
  private maskURL: string = "/../assets/masks/1.jpg";
  private fileReader = new FileReader();
  private maskFileReader = new FileReader();
  private readonly canvasHeight = 750;

  constructor(private http: HttpClient, private renderer: Renderer2) { }

  ngOnInit() {

    this.fileReader.onload = (imgFile) => {
      const data = imgFile.target["result"];
      fabric.Image.fromURL(data, (img) => {
        let oImg = img.set({
          left: 0,
          top: 0,
          angle: 0,
        }).scale(1);
        const multiplier = img.width / img.height;
        img.scaleToHeight(this.canvasHeight);
        //img.filters.push(new fabric.Image.filters.Pixelate);
        //img.applyFilters();
        const canvasElement = <any>document.getElementById("canvas-" + this.index);
        canvasElement.width = this.canvasHeight * multiplier;
        const canvasContainer = <any>document.getElementById('canvas-container-' + this.index);
        canvasContainer.width = this.canvasHeight * multiplier + 12;
        this.canvas = new fabric.Canvas('canvas-' + this.index, {selection: false});
        this.canvas.add(oImg).renderAll();
        // var object = new fabric.Circle({
        //   radius: 15,
        //   fill: 'blue',
        //   left: 100,
        //   top: 100
        // });
        // this.canvas.add(object).renderAll();
        // var a = this.canvas.setActiveObject(oImg);
        // var dataURL = this.canvas.toDataURL({format: 'png', quality: 0.8});
      });
    };
    this.fileReader.readAsDataURL(this.image);

    this.maskFileReader.onload = (imgFile) => {
      const data = imgFile.target["result"];
      fabric.Image.fromURL(data, (img) => {
        let oImg = img.set({
          left: 0,
          top: 0,
          angle: 0,
        }).scale(1);
        this.canvas.add(oImg).renderAll();
      });
    };
    // this.http.get('/assets/masks/1.jpg', {responseType: "blob"}).subscribe((data: Blob) => {
    //   console.log(data);
    //   reader.readAsDataURL(data);
    // })
    //this.addMaskToPicture(1);
  }

  public addMaskToPicture(maskNumber: number) {
      this.getMask(maskNumber).subscribe(data => {
        this.maskFileReader.readAsDataURL(data);
      })
  }

  public getMask(maskNumber: number): Observable<Blob> {
    return this.http.get (this.maskURLs[maskNumber], {responseType: "blob"});
  }

  public save(): void {
    console.log("in save");
    this.onSaveFile.emit({index: this.index, file: this.getCanvasAsFile()})
  }

  public getCanvasAsFile(): File {
    const data = this.canvas.toDataURL({format: 'png'});
    console.log(data);
    //const blob = new Blob([new Uint8Array(data)], {type: 'image/png'});
    var arr = data.split(','),
      mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]),
      n = bstr.length,
      u8arr = new Uint8Array(n);

    while(n--){
      u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], 'filename', {type:mime});
  }

  public applyFilter(filterNumber: number): void {
    const canvasData = this.canvas.toDataURL({});

    const reader = new FileReader();
    reader.onload = (imgFile) => {
      const data = imgFile.target["result"];
      fabric.Image.fromURL(data, (img) => {
        let oImg = img.set({
          left: 0,
          top: 0,
          angle: 0,
        }).scale(1);
        img.filters.push(this.filters[filterNumber].filterObj);
        img.applyFilters();
        this.canvas.clear();
        this.canvas.add(oImg).renderAll();
      });
    };
    reader.readAsDataURL(this.b64toBlob(canvasData));
  }

  b64toBlob(dataURI) {

    var byteString = atob(dataURI.split(',')[1]);
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);

    for (var i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], { type: 'image/jpeg' });
  }

  public removeCurrentObj(): void {
    this.canvas.remove(this.canvas.getActiveObject());
  }
}
