import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Card } from '../card';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';

@Component({
  selector: 'app-create-card',
  templateUrl: './create-card.component.html',
  styleUrls: ['./create-card.component.css']
})
export class CreateCardComponent implements OnInit {

  createCardData = {};

  createCardForm : FormGroup;

  constructor(private _router: Router, private http: HttpClient) { }

  ngOnInit() {

    this.createCardForm = new FormGroup({
        cardName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        type: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        priority: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        label: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        resolution: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        assigneeId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),

    });
  }

  createCard( model: Card, isValid: boolean) {
    this.http.post("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/cards", model, Util.getReqConfig() ).subscribe(
      data => {
        Util.writeSuccess("Creation successful! Card Name, " + model.cardName);
      },
      err => {
        Util.writeError("Card creation failed.");
      }
    );

  }


}