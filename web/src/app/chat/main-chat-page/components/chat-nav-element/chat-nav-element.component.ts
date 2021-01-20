import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';
import { SocketClientService } from '../../../../services/socket-client.service';
import { Observable } from 'rxjs';
import { Message } from '../../../../models/message.model';
import { AuthService } from '../../../../services/auth.service';

@Component({
  selector: 'app-chat-nav-element',
  templateUrl: './chat-nav-element.component.html',
  styleUrls: ['./chat-nav-element.component.scss'],
})
export class ChatNavElementComponent implements OnInit {
  @Input() chat: Chat;
  unreadMessages: number = 0;

  @Input() mainPageEvent: Observable<void>;

  constructor(
    private chatService: ChatService,
    private socketClient: SocketClientService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.chatService
      .getCountUnreadMessagesByChatId(this.chat.id)
      .subscribe((n) => (this.unreadMessages = n));
    this.socketClient.onMessage(`/ws/chats/${this.chat.id}`).subscribe((message: Message) => {
      this.authService.user$.subscribe((currentUser) => {
        if (currentUser.id !== message.sender.id) {
          this.unreadMessages++;
        }
      });
    });
    this.mainPageEvent.subscribe(() => {
      this.unreadMessages = 0;
    });
  }
}
