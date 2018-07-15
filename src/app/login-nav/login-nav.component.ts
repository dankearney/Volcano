import { Component, OnInit } from '@angular/core';
import { Util } from '../utilities/util';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';  
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-login-nav',
  templateUrl: './login-nav.component.html',
  styleUrls: ['./login-nav.component.css']
})
export class LoginNavComponent implements OnInit {

  user: Object;

  constructor(public router: Router) { }

  refreshNavBar() {
  	this.user = Util.getLoggedInUser();
    let team = Util.getCurrentTeam();
    if (this.user != null && team == null && this.router.url != '/teams') {
      this.router.navigate(['teams']);
    }
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
