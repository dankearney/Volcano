import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Card } from '../card';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';


@Component({
  selector: 'app-card-detail-page',
  templateUrl: './card-detail-page.component.html',
  styleUrls: ['./card-detail-page.component.css']
})
export class CardDetailPageComponent implements OnInit {

    cardId;
    cards = [];
    card = null;

    updateCardForm : FormGroup;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.cardId = this.route.snapshot.paramMap.get('card-id');
    this.getCard();

    this.updateCardForm = new FormGroup({
        cardName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        type: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        priority: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        label: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        resolution: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        assigneeId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),

    });
  }

  getCard() {
    this.http.get("https://volcano-backend.herokuapp.com/cards/" + this.cardId, Util.getReqConfig()).subscribe(
      data => {
        this.card = data;
        console.log(this.card);
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  updateCard( model: Card, isValid: boolean) {
    this.http.put("https://volcano-backend.herokuapp.com/cards/" + this.cardId, Util.getReqConfig()).subscribe(
      data => {
        this.card = data;
        Util.writeSuccess("Card has been edited! Card Name, " + model.cardName);
      },
      err => {
        Util.writeGenericError();
      }
    );
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
