import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MOCK_CARDS } from '../mock-cards'

@Component({
  selector: 'app-card-detail-page',
  templateUrl: './card-detail-page.component.html',
  styleUrls: ['./card-detail-page.component.css']
})
export class CardDetailPageComponent implements OnInit {

  cards = MOCK_CARDS;
  card;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.card = this.cards[this.route.snapshot.paramMap.get('card-id')];
  }

}
