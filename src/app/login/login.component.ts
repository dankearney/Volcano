import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Login } from '../login';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Injectable } from "@angular/core";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm : FormGroup;

  constructor(private _router: Router, private http: HttpClient) { }

  ngOnInit() {

    this.loginForm = new FormGroup({
        username: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        password: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        name: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        email: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
    });
  }

  loginUser( model: Login, isValid: boolean) {
  	let token = btoa(model.username + ":" + model.password);
	let config = { headers:  {
	        'Content-Type': 'application/json',
	        'Authorization' : 'Basic ' + token
	    }
	};
    this.http.get("http://volcano-backend.herokuapp.com/login", config).subscribe(
      data => {
        let userPrincipal = data["principal"]["user"];
        alert("Login successful! Welcome, " + userPrincipal.username + "!");
        window.localStorage.setItem("authToken", token);
        window.localStorage.setItem("userPrincipal", JSON.stringify(userPrincipal));
      },
      err => {
        alert("Login failed");
      }
    );
  }


}
