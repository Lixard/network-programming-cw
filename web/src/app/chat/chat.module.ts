import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { MainChatPageComponent } from './main-chat-page/main-chat-page.component';

@NgModule({
  declarations: [MainChatPageComponent],
  imports: [CommonModule, ChatRoutingModule],
  exports: [MainChatPageComponent]
})
export class ChatModule {
}
