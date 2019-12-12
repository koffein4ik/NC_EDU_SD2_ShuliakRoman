import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AddpostService } from '../../services/addpost/addpost.service';
import { StorageUserModel } from '../../models/storageUserModel';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-addpostpage',
  templateUrl: './addpostpage.component.html',
  styleUrls: ['./addpostpage.component.css']
})

export class AddpostpageComponent implements OnInit {

  @ViewChild('myFileInput', {static: false}) myFileInput;

  constructor(private addPostService: AddpostService, private storageService: StorageService,
    private router: Router) { }

  filesToUpload: File[] = [];
  currUser: StorageUserModel;
  successfullyAdded: boolean = false;
  imgurls: string[] = [];

  ngOnInit() {
    this.currUser = this.storageService.getCurrentUser();
    if (!this.currUser) {
      this.router.navigate(['/login']);
    }
  }

  // onClickSubmit(formData): void {
  //   console.log("Submitted");  
  // }

  onClickSubmit(formData): void {
    if (this.filesToUpload.length > 0) {
      if (this.currUser) {
        console.log(this.filesToUpload);
        this.addPostService.postFile(this.filesToUpload, this.currUser.id, formData.description).subscribe(data => {
            this.successfullyAdded = true;
          }, error => {
            console.log(error);
          });
      }
    } else {
      confirm('You have to choose at least one photo to upload');
    }
  }

  removeImage(index: number) {
    this.filesToUpload.splice(index, 1);
    this.imgurls.splice(index, 1);
  }

  handleFileInput(files: FileList) {
    Array.from(files).forEach(file => {
      var reader = new FileReader();
      this.filesToUpload.push(file);
      reader.readAsDataURL(file);
      reader.onload = (event) => {
        this.imgurls.push(reader.result.toString());
      }
    });
    this.myFileInput.nativeElement.value = '';
}

}
