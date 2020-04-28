import {Component, ElementRef, EventEmitter, Input, OnInit, Output, Renderer2, ViewChild} from '@angular/core';
import 'fabric';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import doc from "igniteui-cli/lib/commands/doc";

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

  @ViewChild('canvasElement', {read: undefined, static: false}) el: ElementRef;

  private canvas: any;
  private maskURL: string = "/../assets/masks/1.jpg";
  private fileReader = new FileReader();
  private readonly canvasHeight = 750;

  constructor(private http: HttpClient, private renderer: Renderer2) { }

  ngOnInit() {
    console.log(this.image);
    //const reader = new FileReader();

    this.fileReader.onload = (imgFile) => {
      console.log('in onload');
      const data = imgFile.target["result"];
      fabric.Image.fromURL(data, (img) => {
        let oImg = img.set({
          left: 0,
          top: 0,
          angle: 0,
        }).scale(1);
        const multiplier = img.width / img.height;
        console.log(multiplier);
        img.scaleToHeight(this.canvasHeight);
        img.filters.push(new fabric.Image.filters.Pixelate);
        img.applyFilters();
        const canvasElement = <any>document.getElementById("canvas-" + this.index);
        canvasElement.width = this.canvasHeight * multiplier;
        const canvasContainer = <any>document.getElementById('canvas-container-' + this.index);
        canvasContainer.width = this.canvasHeight * multiplier + 12;
        console.log(canvasContainer);
        console.log(this.canvasHeight * multiplier);
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
    // this.http.get('/assets/masks/1.jpg', {responseType: "blob"}).subscribe((data: Blob) => {
    //   console.log(data);
    //   reader.readAsDataURL(data);
    // })
    //this.addMaskToPicture(1);
  }

  public addMaskToPicture(maskNumber: number) {
      this.getMask(1).subscribe(data => {
        this.fileReader.readAsDataURL(data);
      })
  }

  public getMask(maskNumber: number): Observable<Blob> {
    return this.http.get ('/assets/masks/' + maskNumber + '.jpg', {responseType: "blob"});
  }

  public save(): void {
    this.onSaveFile.emit({index: this.index, file: this.getCanvasAsFile()})
  }

  public getCanvasAsFile(): File {
    const data = this.canvas.toDataURL({format: 'png'});
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

}
