import { Component, OnInit } from '@angular/core';
import { Util } from '../utilities/util';
import { CommonModule } from '@angular/common';  
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-login-nav',
  templateUrl: './login-nav.component.html',
  styleUrls: ['./login-nav.component.css']
})
export class LoginNavComponent implements OnInit {

  user: Object;

  constructor() { }

  refreshNavBar() {
  	this.user = Util.getLoggedInUser();
  }


  ngOnInit() {
  	this.refreshNavBar();
  	setInterval(() => { this.refreshNavBar() }, 1000);
  }

  logout() {
  	Util.logOut();
  	this.refreshNavBar();
  	Util.writeSuccess("Logged out successfully");
  }

}
