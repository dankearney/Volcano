import { Component, OnInit } from '@angular/core';
import { Chat } from '../chat';
import { MOCK_CHATS } from '../mock-chats'

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit {

  chats = MOCK_CHATS;

  constructor() { }

  // THIS IS A KLUDGE, DO NOT REPLICATE
  onSubmit(event: any) { 
  	let message = event.target[0].value;
  	let newchat = new Chat('Dan', message);
  	this.chats.push(newchat);
  }

  ngOnInit() {
  }

}
