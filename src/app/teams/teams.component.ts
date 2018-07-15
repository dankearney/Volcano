import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Util } from '../utilities/util';
import { Team } from '../team';
import { TeamUserMembership } from '../teamUserMembership';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {

  myTeams = null;
  publicTeams = null;
  currentTeam = null;

  public teamForm : FormGroup;


  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.teamForm = new FormGroup({
      teamName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
      type: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)])
    });

    this.currentTeam = Util.getCurrentTeam();

    this.loadTeams();

  }

  loadTeams() {
    this.http.get("https://volcano-backend.herokuapp.com/teams", Util.getReqConfig()).subscribe(
      data => {
        this.publicTeams = data;
      },
      err => {
        Util.writeGenericError();
      }
    );

    this.http.get("https://volcano-backend.herokuapp.com/myTeams", Util.getReqConfig()).subscribe(
      data => {
        this.myTeams = data;
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  createTeam(model: Team, isValid: boolean) { 
	this.http.post("https://volcano-backend.herokuapp.com/teams", model, Util.getReqConfig()).subscribe(
	    data => {
	      Util.writeSuccess("Team successfully created");
	      this.loadTeams();
	    },
	    err => {
	      Util.writeGenericError();
	    }
   	);
  } 

  selectTeam(team: Team) {
  	Util.setCurrentTeam(team);
   	this.currentTeam = team;
  	Util.writeSuccess("Logged in to team " + team.teamName);
  } 	

  joinTeam(team: Team) {
  	let tum = { teamId : team.teamId };
	this.http.post("https://volcano-backend.herokuapp.com/teamUserMemberships", tum, Util.getReqConfig()).subscribe(
	    data => {
	      Util.writeSuccess("Successfully joined " + team.teamName);
	      this.loadTeams();
	    },
	    err => {
	      Util.writeGenericError();
	    }
   	);
  }

}
