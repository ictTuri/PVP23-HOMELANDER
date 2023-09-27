import { Component } from '@angular/core';

@Component({
  selector: 'hl-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  public currentYear: number;

  constructor() {
    this.currentYear = new Date().getFullYear();
  }
}
