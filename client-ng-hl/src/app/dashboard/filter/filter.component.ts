import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PropertyService } from '../../services/property.service';
import { Area, City, Country } from '../../models/app.interface';
import { StaticData } from './static.data';

@Component({
  selector: 'hl-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent implements OnInit {

  constructor(private _propertyService: PropertyService) { }

  @Output() filterApplied = new EventEmitter<any>();

  selectedCountry: Country = null;
  selectedCity: City = null;
  cities: City[] = [];
  countries: Country[] = [];
  zones: Area[] = [];
  generalMinPrices: any = StaticData.minGeneralPrice;
  generalMaxPrices: any = StaticData.maxGeneralPrice;
  generalMinFiltered = this.generalMinPrices;
  generalMaxFiltered = this.generalMaxPrices;
  minRentPrices: any = StaticData.minRentPrice;
  maxRentPrices: any = StaticData.maxRentPrice;
  minRentFiltered = this.minRentPrices;
  maxRentFiltered = this.maxRentPrices;
  minSalePrices: any = StaticData.minSalePrice;
  maxSalePrices: any = StaticData.maxSalePrice;
  minSaleFiltered = this.minSalePrices;
  maxSaleFiltered = this.maxSalePrices;
  selectedMinPrice: number = null;
  selectedMaxPrice: number = null;
  selectedZones: string[] = [];

  filter = {
    country: null,
    city: null,
    zones: [],
    owner: null,
    forSale: null,
    forRent: null,
    typology: null,
    rooms: null,
    furnished: null,
    priceRange: {
      from: null,
      to: null
    },
    sizeRange: {
      from: null,
      to: null
    }
  }

  ngOnInit(): void {
    this._propertyService.getCountries().subscribe(
      {
        next: responseData => {
          this.countries = responseData;
        },
        error: error => {
          console.error('Error fetching countries:', error);
        }
      });
    this.filterApplied.emit(this.filter);
  }

  applyFilter() {
    const newFilter = { ...this.filter }
    this.filterApplied.emit(newFilter);
  }

  onCountrySelect(name: string) {
    if (name.length > 1) {
      this.filter.country = name;
      this.filter.city = null;
      this.filter.zones = [];
      this.selectedZones = [];
      this.selectedCountry = this.countries.filter(item => item.name === name).pop();
      this._propertyService.getCitiesByCountry("" + this.selectedCountry.id)
        .subscribe(cities => {
          this.cities = cities;
        });
    } else {
      this.selectedCountry = null;
      this.filter.country = null;
    }
  }

  onCitySelect(name: string) {
    if (name.length > 1) {
      this.filter.city = name;
      this.selectedCity = this.cities.filter(item => item.name === name).pop();
      this._propertyService.getAreasByCity("" + this.selectedCity.id)
        .subscribe(zones => {
          this.zones = zones;
        });
    } else {
      this.selectedCity = null;
      this.filter.city = null;
    }
  }

  onZoneSelect(name: string[]) {
    console.log(name)
  }

  onTypologySelect(name: string) {
    if (name.length > 1) {
      this.filter.typology = name;
    } else {
      this.filter.typology = null;
    }
  }

  pushCheckBoxValue(name: string) {
    if (this.filter.zones.indexOf(name) !== -1) {
      let index = this.filter.zones.indexOf(name);
      this.filter.zones.splice(index, 1);
      this.selectedZones.splice(index, 1);
    } else {
      this.selectedZones.push(name);
      this.filter.zones.push(name);
    }
  }

  onGeneralMinPriceSet(number: number) {
    this.selectedMinPrice = number;
    this.filter.priceRange.from = number;
    this.generalMaxFiltered = this.generalMaxPrices.filter(value => value.value > number)
  }

  onRentMinPriceSet(number: number) {
    this.selectedMinPrice = number;
    this.filter.priceRange.from = number;
    this.maxRentFiltered = this.maxRentPrices.filter(value => value.value > number)
  }

  onSaleMinPriceSet(number: number) {
    this.selectedMinPrice = number;
    this.filter.priceRange.from = number;
    this.maxSaleFiltered = this.maxSalePrices.filter(value => value.value > number)
  }

  onGeneralMaxPriceSet(number: number) {
    this.selectedMaxPrice = number;
    this.filter.priceRange.to = number;
    this.generalMinFiltered = this.generalMinPrices.filter(value => value.value < number)
  }

  onRentMaxPriceSet(number: number) {
    this.selectedMaxPrice = number;
    this.filter.priceRange.to = number;
    this.minRentFiltered = this.minRentPrices.filter(value => value.value < number)
  }

  onSaleMaxPriceSet(number: number) {
    this.selectedMaxPrice = number;
    this.filter.priceRange.to = number;
    this.minSaleFiltered = this.minSalePrices.filter(value => value.value < number)
  }

  setRooms(event: any) {
    this.filter.rooms = event.target.value;
  }

  setForSaleValue() {
    this.clearSelectedPrice();
    this.filter.forRent = null;
    this.filter.forSale = true;
  }

  setForRentValue() {
    this.clearSelectedPrice();
    this.filter.forSale = null;
    this.filter.forRent = true;
  }

  private clearSelectedPrice() {
    this.selectedMaxPrice = null;
    this.filter.priceRange.from = null;
    this.filter.priceRange.to = null;
    this.selectedMinPrice = null;
  }
}
