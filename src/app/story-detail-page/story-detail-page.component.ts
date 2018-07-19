import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Card } from '../card';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';
import { Story } from '../story';

@Component({
  selector: 'app-story-detail-page',
  templateUrl: './story-detail-page.component.html',
  styleUrls: ['./story-detail-page.component.css']
})
export class StoryDetailPageComponent implements OnInit {
  storyId;
  stories = [];
  story = null;


constructor(private route: ActivatedRoute, private http: HttpClient) { }

ngOnInit() {
  this.storyId = this.route.snapshot.paramMap.get('story-id');
  //this.getStory();
}

getCard() {
  this.http.get("https://volcano-backend.herokuapp.com/cards/" + this.storyId, Util.getReqConfig()).subscribe(
    data => {
      this.story = data;
      console.log(this.story);
    },
    err => {
      Util.writeGenericError();
    }
  );
}
 

}
