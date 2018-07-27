import { Component, OnInit } from '@angular/core';
import { Chat } from '../chat';
import { MOCK_CHATS } from '../mock-chats'
import { Http, Response } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Util } from '../utilities/util';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit {

  chats = null;

  public chatForm : FormGroup;

  constructor(private http: HttpClient) { }

  sendChatMessage(model: Chat, isValid: boolean) { 
      this.http.post("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/chat", model, Util.getReqConfig()).subscribe(
      data => {
          this.chats.push(data);
          // Clear the input
          document.getElementById("sendMessage")["value"] = "";
          setTimeout(this.setScrollPosition, 50);
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  refreshChats() {
    this.http.get("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/chats", Util.getReqConfig()).subscribe(
      data => {
        // Refresh UI initially, and if there are new chats.
        if (this.chats == null || this.chats["length"] != data["length"]) {
          this.chats = data;
          setTimeout(this.setScrollPosition, 50);
        }
      },
      err => {
        Util.writeGenericError();
      }
    );
  }

  setScrollPosition() {
    var objDiv = document.getElementById("chatcontainer");
    objDiv.scrollTop = 10000;
  }

  ngOnInit() {

      this.chatForm = new FormGroup({
        message: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)])
      });

      this.refreshChats();
      setInterval(() => { this.refreshChats() }, 5000);

  }

}
