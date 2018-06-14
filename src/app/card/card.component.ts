import { Component, OnInit, Input } from '@angular/core';
import { Card } from '../card';
import { MOCK_CARD } from '../mock-card';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})

export class CardComponent implements OnInit {

  @Input() public card : Card;

  constructor() { }
  
  ngOnInit() {
  }

}
