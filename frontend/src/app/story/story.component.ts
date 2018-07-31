import { Component, OnInit, Input } from '@angular/core';
import { Story } from '../story';

@Component({
  selector: 'app-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.css']
})

export class StoryComponent implements OnInit {

  @Input() public story : Story;
  ticketsInProgress = 0;
  ticketsIncomplete = 0;
  ticketsComplete = 0;

  constructor() { }

  ngOnInit() {
  	console.log(this.story);
  	if (this.story['cardsAttached'] != null) {
  	  	for ( var i = 0; i<this.story['cardsAttached'].length; i++ ) {
  	  		var cardStatus = (this.story['cardsAttached'][i])['resolution'];
  	  		console.log(cardStatus);
  	  		if (new String(cardStatus).valueOf() == new String('Incomplete').valueOf()) {
  	  			this.ticketsIncomplete += 1;
  	  		}
  	  		if (new String(cardStatus).valueOf() == new String('In Progress').valueOf()) {
  	  			this.ticketsInProgress += 1;
  	  		}
  	  		if (new String(cardStatus).valueOf() == new String('Complete').valueOf()) {
  	  			this.ticketsComplete += 1;
  	  		}
  	  	}
  	}
  }

}


