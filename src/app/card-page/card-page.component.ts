import { Component, OnInit } from '@angular/core';
import { MOCK_CARDS } from '../mock-cards'
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'app-card-page',
  templateUrl: './card-page.component.html',
  styleUrls: ['./card-page.component.css']
})
export class CardPageComponent implements OnInit {

  cards = MOCK_CARDS;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  	let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
	let config = { headers:  {
	        'Content-Type': 'application/json',
	        'Authorization' : 'Basic ' + window.localStorage.getItem("authToken")
	    }
	};
    this.http.get("http://volcano-backend.herokuapp.com/cards", config).subscribe(
      data => {
        this.cards = data.content;
      },
      err => {
        alert("Login failed");
      }
    );
  }

}
