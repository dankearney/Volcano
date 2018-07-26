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
  public joinTeamForm : FormGroup;

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.teamForm = new FormGroup({
      teamName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
      password: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)])
    });

    this.joinTeamForm = new FormGroup({
      password: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)])
    });

    this.currentTeam = Util.getCurrentTeam();

    this.loadTeams();

  }

  loadTeams() {

    this.http.get("https://volcano-backend.herokuapp.com/myTeams", Util.getReqConfig()).subscribe(
      data => {
        this.myTeams = data;
        if (this.myTeams.length == 0) {
        	document.getElementById("joinableTeams-tab").click();
        	Util.writeError("You are not a member of any teams, join or create one to continue.");
        }
        this.publicTeams = [];
        this.http.get("https://volcano-backend.herokuapp.com/teams", Util.getReqConfig()).subscribe(
	      data2 => {
	      	// Only show teams I'm not part of as options to join
	      	let myTeamIds = [];
	      	for (var myTeamId in this.myTeams) {
	      		myTeamIds.push(this.myTeams[myTeamId]["teamId"]);
	      	}
	      	for (var teamIdx in data2) {
	      		if ( !( myTeamIds.includes(data2[teamIdx]["teamId"] ) ) )  {
	      			this.publicTeams.push(data2[teamIdx]);
	      		}
	      	}
	      	if (Util.getCurrentTeam() == null) {
				Util.setCurrentTeam(this.myTeams[0]);
   				this.currentTeam = this.myTeams[0];
   			}
	      },
	      err => {
	        Util.writeGenericError();
	      }
	    );
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  createTeam(model: Team, isValid: boolean) { 
  	if (!isValid) {
  		Util.writeError("Invalid password or team name.");
  	}
  	else 
  	{
		this.http.post("https://volcano-backend.herokuapp.com/teams", model, Util.getReqConfig()).subscribe(
		    data => {
		      Util.writeSuccess("Team successfully created");
		  	  Util.setCurrentTeam(model);
		   	  this.currentTeam = model;
		      this.loadTeams();
		    },
		    err => {
		      Util.writeGenericError();
		    }
	   	);
	}
  } 

  selectTeam(team: Team) {
  	Util.setCurrentTeam(team);
   	this.currentTeam = team;
  	Util.writeSuccess("Logged in to team " + team.teamName);
  } 	

  joinTeam(model: Object, isValid: boolean, team: Team) {
  	console.log(model);
  	let tum = { teamId : team.teamId, password : model["password"] };
	this.http.post("https://volcano-backend.herokuapp.com/teamUserMemberships", tum, Util.getReqConfig()).subscribe(
	    data => {
	      Util.writeSuccess("Successfully joined " + team.teamName);
	   	  Util.setCurrentTeam(team);
	   	  this.currentTeam = team;
	      this.loadTeams();
	    },
	    err => {
	      Util.writeError("Unable to join team, password may be incorrect.");
	    }
   	);
  }

}
