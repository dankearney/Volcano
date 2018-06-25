import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';

import { FormsModule } from '@angular/forms';
import { ChatPageComponent } from './chat-page/old-page/chat-page.component'; // <-- NgModel lives here

import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './default/default.component';
import { CardComponent } from './card/card.component';
import { CardPageComponent } from './card-page/card-page.component';
import { StoryComponent } from './story/story.component';
import { StoryPageComponent } from './story-page/story-page.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CardDetailPageComponent } from './card-detail-page/card-detail-page.component';
import { CardEditPageComponent } from './card-edit-page/card-edit-page.component';

const appRoutes: Routes = [
  { path: 'chat', component: ChatPageComponent },
  { path: 'cards', component: CardPageComponent },
  { path: 'stories', component: StoryPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', component: DefaultComponent },
  { path: 'cardDetail/:card-id', component: CardDetailPageComponent }
  { path: 'cardEdit/:card-id', component: CardEditPageComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    ChatPageComponent,
    DefaultComponent,
    CardComponent,
    CardPageComponent,
    StoryComponent,
    StoryPageComponent,
    LoginComponent,
    RegisterComponent,
    CardDetailPageComponent,
    CardEditPageComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
    // other imports
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
