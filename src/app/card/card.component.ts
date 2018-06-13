import { Component, OnInit } from '@angular/core';
import { MOCK_CARD } from '../mock-card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent implements OnInit {

  card = MOCK_CARD;

  constructor() { }

  ngOnInit() {
  }

}
