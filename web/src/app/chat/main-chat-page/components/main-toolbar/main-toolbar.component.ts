import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../../services/auth.service';
import { switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Chat } from '../../../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';

@Component({
  selector: 'app-main-toolbar',
  templateUrl: './main-toolbar.component.html',
  styleUrls: ['./main-toolbar.component.scss'],
})
export class MainToolbarComponent implements OnInit {
  @Input() chat: Chat;

  constructor(public auth: AuthService, private chatService: ChatService, private router: Router) {}

  ngOnInit(): void {}

  logout(): void {
    this.auth.user$.subscribe((user) => console.log(user));
    this.auth
      .logout()
      .pipe(switchMap(() => this.auth.loadProfile()))
      .subscribe((user) => {
        this.auth.user$.next(user);
        this.router.navigateByUrl('/login');
      });
  }

  deleteChat() {
    this.chatService.removeChat(this.chat.id).subscribe(() => (this.chat = null));
  }
}
