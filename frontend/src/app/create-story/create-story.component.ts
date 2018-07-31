import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Story } from '../story';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrls: ['./create-story.component.css']
})

export class CreateStoryComponent implements OnInit {

  createStoryData = {};

  public createStoryForm : FormGroup;

  constructor(private _router: Router, private http: HttpClient) { }

  ngOnInit() {

    this.createStoryForm = new FormGroup({
       storyName: new FormControl('', [<any>Validators.required, <any><any>Validators.minLength(3)]),
       resolution: new FormControl('', [<any>Validators.required, <any><any>Validators.minLength(3)]),
       description: new FormControl('', [<any>Validators.required, <any><any>Validators.minLength(3)]),
    });
    this.createStoryForm.patchValue( { "resolution" : "Incomplete" } );
  }

  createStory( model: Story, isValid: boolean) {
  	  if (!isValid) {
  	      Util.writeError("Something went wrong. Project name and description cannot be blank.");
  	  }
  	  else
  	  {
	      this.http.post("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/stories", model, Util.getReqConfig() ).subscribe(
	          data => {
	            Util.writeSuccess("Project created successfully.");
	          },
	          err => {
	            Util.writeError("Story creation failed.");
	          }
	        );
      }
    }
}
  
