import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
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
  updateStoryForm : FormGroup;

  constructor(private _router: Router, private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
    this.storyId = this.route.snapshot.paramMap.get('story-id');
    this.getStory();

    this.updateStoryForm = new FormGroup({
        storyId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        storyName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        resolution: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
       
    });

  }

  getStory() {
    this.http.get("https://volcano-backend.herokuapp.com/stories/" + this.storyId, Util.getReqConfig()).subscribe(
      data => {
        this.story = data;
        // Populate form values
        this.updateStoryForm.patchValue({storyId: this.story.storyId});
        this.updateStoryForm.patchValue({storyName: this.story.storyName});
    
        this.updateStoryForm.patchValue({resolution: this.story.resolution});
        this.updateStoryForm.patchValue({description: this.story.description});

      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  updateStory(model: Story, isValid: boolean) {
    if (model.storyName.length == 0) {
      Util.writeError("Project name cannot be empty.");
    }
    else
    {
	    this.http.put("https://volcano-backend.herokuapp.com/stories/" + this.storyId, model, Util.getReqConfig() ).subscribe(
	      data => {
	        this.story = data;
	        Util.writeSuccess("Project updated successfully.");
	      },
	      err => {
	        Util.writeGenericError();
	      }
	    );
    }
  }

  createStory( model: Story, isValid: boolean) {
    this.http.post("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/stories", model, Util.getReqConfig() ).subscribe(
      data => {
        Util.writeSuccess("Project created successfully.");
      },
      err => {
        Util.writeError("Project creation failed.");
      }
    );

  }

  deleteStory() {
   this.http.delete("https://volcano-backend.herokuapp.com/stories/" + this.storyId, Util.getReqConfig() ).subscribe(
      data => {
        Util.writeSuccess("Project delete successfully.");
        this._router.navigate(["/stories"]);
       
      },
      err => {
        Util.writeError("Project deletion failed.");
      }
    );
  }

}




