import { Component, Input } from '@angular/core';
import { Property } from '../../../models/app.interface';

@Component({
  selector: 'hl-property',
  templateUrl: './property.component.html',
  styleUrl: './property.component.css'
})
export class PropertyComponent {
    @Input() property: Property;
}
