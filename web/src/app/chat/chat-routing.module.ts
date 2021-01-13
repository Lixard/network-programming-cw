import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainChatPageComponent } from './main-chat-page/main-chat-page.component';

const routes: Routes = [
  {
    path: '',
    component: MainChatPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ChatRoutingModule {
}
