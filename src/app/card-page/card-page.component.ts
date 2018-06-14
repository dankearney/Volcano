import { Component, OnInit } from '@angular/core';
import { MOCK_CARDS } from '../mock-cards'

@Component({
  selector: 'app-card-page',
  templateUrl: './card-page.component.html',
  styleUrls: ['./card-page.component.css']
})
export class CardPageComponent implements OnInit {

  cards = MOCK_CARDS;

  constructor() { }

  ngOnInit() {
  }

}
