import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Register } from '../register';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerUserData = {};

  public registerForm : FormGroup;

  constructor(private _router: Router, private http: HttpClient) { }

  ngOnInit() {

    this.registerForm = new FormGroup({
        username: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        password: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        name: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        email: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
    });
  }

  registerUser( model: Register, isValid: boolean) {
	const config = { headers:  {
    	'Content-Type': 'application/json',
    } };
    this.http.post("https://volcano-backend.herokuapp.com/register", model, config ).subscribe(
      data => {
        Util.writeSuccess("Creation successful! Welcome, " + model.username + ". Log in to continue.");
        this._router.navigate(['login']);
      },
      err => {
        Util.writeError("Account creation failed. Username may be in use.");
      }
    );

  }


}
