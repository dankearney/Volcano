import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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


  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.cardId = this.route.snapshot.paramMap.get('card-id');
    this.getCard();
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

}
