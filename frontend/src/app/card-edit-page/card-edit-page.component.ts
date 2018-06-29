import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MOCK_CARDS } from '../mock-cards'

@Component({
  selector: 'app-card-edit-page',
  templateUrl: './card-edit-page.component.html',
  styleUrls: ['./card-edit-page.component.css']
})
export class CardEditPageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
