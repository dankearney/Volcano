import { NgModule } from '@angular/core';
import {Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CreateCardComponent } from './create-card/create-card.component';
import { CreateStoryComponent } from './create-story/create-story.component';

const routes: Routes = [
  {path: 'create-card', component: CreateCardComponent},
  {path: 'create-story', component: CreateStoryComponent}
];


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: []
})
export class AppRoutingModule { }
//add more component in array with rout
export const routingComponent = [CreateCardComponent, CreateStoryComponent]
