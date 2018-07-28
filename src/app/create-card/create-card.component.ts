import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Card } from '../card';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';
import { Util } from '../utilities/util';

@Component({
  selector: 'app-create-card',
  templateUrl: './create-card.component.html',
  styleUrls: ['./create-card.component.css']
})
export class CreateCardComponent implements OnInit {

  createCardData = {};

  createCardForm : FormGroup;

  assignees;

  constructor(private _router: Router, private http: HttpClient) { }

  ngOnInit() {

    this.createCardForm = new FormGroup({
        cardName: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        type: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        priority: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        label: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        resolution: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        assigneeId: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)]),
        dueDate: new FormControl('', [<any>Validators.required, <any>Validators.minLength(3)])
    });
    this.createCardForm.patchValue({type: 'Bug'});
    this.createCardForm.patchValue({priority: 'Normal'});
    this.createCardForm.patchValue({label: 'Frontend'});
    this.createCardForm.patchValue({resolution: 'Incomplete'});
    this.createCardForm.patchValue({resolution: 'Incomplete'});
    this.createCardForm.patchValue({dueDate : (new Date()).toISOString().split('T')[0] });
    this.createCardForm.patchValue({assigneeId : 1 });
    this.http.get("https://volcano-backend.herokuapp.com/teams/" + Util.getCurrentTeamId(), Util.getReqConfig() ).subscribe(
      data => {
        this.assignees = data['teamUserMemberships'];
        this.createCardForm.patchValue({assigneeId : this.assignees[0]['userId']})
      },
      err => {
        Util.writeError("could not read team members.");
      }
    );
  }

  createCard( model: Card, isValid: boolean) {
    if (model.cardName.length == 0) {
      Util.writeError("Ticket name cannot be empty.");
    }
    else
    {
      this.http.post("https://volcano-backend.herokuapp.com/team/" + Util.getCurrentTeamId() + "/cards", model, Util.getReqConfig() ).subscribe(
        data => {
          Util.writeSuccess("Creation successful! Card Name, " + model.cardName);
        },
        err => {
          Util.writeError("Card creation failed.");
        }
      );
    }

  }

}