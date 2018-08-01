import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../../utilities/util';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {

  teamId;
  team = null;

  constructor(private _router: Router, private route: ActivatedRoute, private http: HttpClient) { }

  refresh() {
  	this.teamId = this.route.snapshot.paramMap.get('team-id');
    this.http.get("https://volcano-backend.herokuapp.com/teams/" + Util.getCurrentTeamId(), Util.getReqConfig()).subscribe(
      data => {
        this.team = data;
        let loggedInUserId = Util.getLoggedInUser()["userid"];
        this.team.teamUserMemberships.forEach((tum, index) => {
  			if (tum.userId == loggedInUserId && tum.teamId == 'null') {
          err => {
            Util.writeGenericError();
          }  			}
		});
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  ngOnInit() {
  	this.refresh();
  }

}
