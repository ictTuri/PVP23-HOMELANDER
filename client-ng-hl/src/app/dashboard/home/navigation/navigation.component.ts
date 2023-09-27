import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../../models/app.interface';
import { UserService } from '../../../services/user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'hl-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent implements OnInit, OnDestroy{
  user: User = null;
  authenticated: boolean = false; 
  subscription!: Subscription;

  constructor(private _userService: UserService) {}

  ngOnInit(): void {
    this.authenticated = true;
  }
  
  ngOnDestroy(): void {
    // this.subscription.unsubscribe();  
  }

}
