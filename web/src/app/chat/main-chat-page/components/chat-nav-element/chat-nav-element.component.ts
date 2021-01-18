import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';

@Component({
  selector: 'app-chat-nav-element',
  templateUrl: './chat-nav-element.component.html',
  styleUrls: ['./chat-nav-element.component.scss'],
})
export class ChatNavElementComponent implements OnInit {
  @Input() chat: Chat;
  unreadMessages: number = 0;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.chatService.getCountUnreadMessagesByChatId(this.chat.id).subscribe((n) => {
      console.log('unreadMessages = ', n);
      this.unreadMessages = n;
    });
  }
}
