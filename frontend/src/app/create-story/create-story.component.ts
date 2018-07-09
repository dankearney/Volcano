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
        storyId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        number: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        storyName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        Type: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        priority: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        label: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        resolution: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        attachment: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        userId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        teamId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),

    });
  }

    createStory( model: Story, isValid: boolean) {
      console.log("CODE RUNNING");
      this.http.post("https://volcano-backend.herokuapp.com/stories", model, Util.getReqConfig() ).subscribe(
        data => {
          Util.writeSuccess("Creation successful! Story Name, " + model.storyName);
        },
        err => {
          Util.writeError("Story creation failed.");
        }
      );
  }
}

