import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FileDto } from '../../../models/app.interface';

@Component({
  selector: 'app-uploadimage',
  templateUrl: './uploadimage.component.html',
  styleUrl: './uploadimage.component.css'
})
export class UploadimageComponent implements OnInit {
  public fileDto: FileDto;
  public selectedFiles?: FileList;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { image: FileDto }) {
    this.fileDto = data.image;
  }

  ngOnInit(): void {
    console.log(this.fileDto);
  }

  saveImage(): void {

  }

  selectFiles(event): void {
    this.selectedFiles = event.target.files;
  }

}
