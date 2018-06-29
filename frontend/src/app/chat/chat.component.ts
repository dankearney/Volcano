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

  onSubmit(form: any) { 
  	let newchat = new Chat('Dan', form.message);
  	this.chats.push(newchat);
  }

  ngOnInit() {
  }

}
