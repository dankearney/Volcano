import { Component, OnInit } from '@angular/core';
import { Story } from '../story';
import { MOCK_STORY } from '../mock-story';
@Component({
  selector: 'app-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.css']
})
export class StoryComponent implements OnInit {
story = MOCK_STORY;
  constructor() { }

  ngOnInit() {
  }

}
