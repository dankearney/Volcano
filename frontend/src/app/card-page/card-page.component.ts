import { Component, OnInit } from '@angular/core';
import { MOCK_CARDS } from '../mock-cards'
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';

@Component({
  selector: 'app-card-page',
  templateUrl: './card-page.component.html',
  styleUrls: ['./card-page.component.css']
})
export class CardPageComponent implements OnInit {

  cards = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get("https://volcano-backend.herokuapp.com/cards", Util.getReqConfig()).subscribe(
      data => {
        this.cards = <any[]>data;
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

}
