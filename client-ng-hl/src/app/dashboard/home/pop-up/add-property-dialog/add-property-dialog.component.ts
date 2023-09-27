import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FilesService } from '../../../../services/files.service';
import { Cities, Countries } from '../../../filter/dropdown-values.data';
import { PropertyService } from '../../../../services/property.service';
import { Observable, forkJoin, map } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-property-dialog',
  templateUrl: './add-property-dialog.component.html',
  styleUrl: './add-property-dialog.component.css',
})
export class AddPropertyDialogComponent {

  constructor(private _filesService: FilesService,
    private _propertyService: PropertyService,
    private _toastr: ToastrService) { }

  cities = Cities;
  countries = Countries;
  selectedFiles?: FileList;
  isLoading: boolean = false;

  propertyForm = new FormGroup({
    description: new FormControl(null),
    longDescription: new FormControl(null),
    size: new FormControl(null),
    unit: new FormControl('m'),
    year: new FormControl(1990),
    price: new FormControl(10),
    propertyAddress: new FormGroup({
      country: new FormControl(null),
      city: new FormControl(null),
      zone: new FormControl(null),
      address: new FormControl(null),
      floorNumber: new FormControl(null),
      doorNumber: new FormControl(null),
      street: new FormControl(null)
    }),
    additionalAttributes: new FormGroup({
      furnished: new FormControl(false),
      typology: new FormControl(null),
      type: new FormControl(null),
      appliances: new FormControl(null),
      images: new FormControl<string[]>(null)
    })
  });

  onSubmit() {
    if (this.selectedFiles) {
      this.uploadFiles().subscribe({
        next: (stringsList: string[]) => {
          this.propertyForm.get('additionalAttributes.images').setValue(stringsList);
          this._propertyService.addProperties(this.propertyForm).subscribe({
            next: () => {
              this.isLoading = false;
              this._toastr.success('Property added successfully!');
              window.location.reload();
            },
            error: error => {
              this.applyOnError(this.propertyForm, error);
            }
          });
        }
      });
    } else {
      this._propertyService.addProperties(this.propertyForm).subscribe({
        next: () => {
          this.isLoading = false;
          this._toastr.success('Property added successfully!');
          window.location.reload();
        },
        error: error => {
          this.applyOnError(this.propertyForm, error);
        }
      });
    }

  }

  onImageSelected(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement?.files && inputElement.files.length > 0) {
      this.selectedFiles = inputElement.files;
    }
  }

  selectFiles(event): void {
    this.selectedFiles = event.target.files;
  }

  uploadFiles(): Observable<string[]> {
    this.isLoading = true;
    const observables: Observable<string>[] = [];
    for (let i = 0; i < this.selectedFiles.length; i++) {
      observables.push(this._filesService.upload(this.selectedFiles[i]).pipe(
        map(response => {
          return response.uuid;
        })
      ));
    }
    return forkJoin(observables);
  }

  private applyOnError(form: FormGroup, error: any) {
    form.reset();
    this.isLoading = false;
    this._toastr.error(error);
  }
}
