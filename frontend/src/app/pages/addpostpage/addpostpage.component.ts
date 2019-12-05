import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AddpostService } from '../../services/addpost/addpost.service';

@Component({
  selector: 'app-addpostpage',
  templateUrl: './addpostpage.component.html',
  styleUrls: ['./addpostpage.component.css']
})
export class AddpostpageComponent implements OnInit {

  constructor(private addPostService: AddpostService) { }

  filesToUpload: File[] = [];

  ngOnInit() {
  }

  // onClickSubmit(formData): void {
  //   console.log("Submitted");  
  // }

  onClickSubmit(formData): void {
    console.log(this.filesToUpload);
    this.addPostService.postFile(this.filesToUpload, +localStorage.getItem('userid'), formData.description).subscribe(data => {
        console.log("Success");
      }, error => {
        console.log(error);
      });
  }

  handleFileInput(files: FileList) {
    Array.from(files).forEach(file => {
      this.filesToUpload.push(file);
    });
    //this.filesToUpload.push(files);
    console.log(this.filesToUpload);
}

}
