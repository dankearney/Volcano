import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Story } from '../story'

@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrls: ['./create-story.component.css']
})
export class CreateStoryComponent implements OnInit {

  public createStoryForm : FormGroup;

  constructor() { }

  ngOnInit() {
  }
  
  createStory(model: Story, isValid: boolean) { }

}

