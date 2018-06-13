import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';

import { FormsModule } from '@angular/forms';
import { ChatPageComponent } from './chat-page/chat-page.component'; // <-- NgModel lives here

import { RouterModule, Routes } from '@angular/router';
import { DefaultComponent } from './default/default.component';
import { CardComponent } from './card/card.component';
import { CardPageComponent } from './card-page/card-page.component';

const appRoutes: Routes = [
  { path: 'chat', component: ChatPageComponent },
  { path: 'cards', component: CardPageComponent },
  { path: '', component: DefaultComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    ChatPageComponent,
    DefaultComponent,
    CardComponent,
    CardPageComponent,
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
