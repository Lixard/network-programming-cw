import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainChatPageComponent } from './main-chat-page/main-chat-page.component';
import { AuthGuard } from '../guards/auth-guard.service';

const routes: Routes = [
  {
    path: '',
    component: MainChatPageComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ChatRoutingModule {}
