import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {

  teamId;
  team = null;
  isAdmin = false;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
  	this.teamId = this.route.snapshot.paramMap.get('team-id');
    this.http.get("https://volcano-backend.herokuapp.com/teams/" + this.teamId, Util.getReqConfig()).subscribe(
      data => {
        this.team = data;
        this.isAdmin = false;
        let loggedInUserId = Util.getLoggedInUser()["userid"];
        this.team.teamUserMemberships.forEach((tum, index) => {
  			if (tum.userId == loggedInUserId && tum.status == 'Admin') {
  				this.isAdmin = true;
  			}
		});
      },
      err => {
        Util.writeGenericError();
      }
    );

  }

  removeFromTeam(teamUserMembershipId : string) {
  	alert(teamUserMembershipId);
  }

}
