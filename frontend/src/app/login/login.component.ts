import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardPageComponent } from '../card-page/card-page.component';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUserData = {}
  constructor(private _router: Router) { }

  ngOnInit() {
  }

  loginUser() {
  }
}
