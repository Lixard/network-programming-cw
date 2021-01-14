import { Component, Input, OnInit } from '@angular/core';
import { ChatListElement } from '../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';

@Component({
  selector: 'app-chat-nav-element',
  templateUrl: './chat-nav-element.component.html',
  styleUrls: ['./chat-nav-element.component.css'],
})
export class ChatNavElementComponent implements OnInit {
  @Input() chat: ChatListElement;
  unreadMessages: number;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.chatService
      .getCountUnreadMessagesByChatId(this.chat.id)
      .subscribe((e) => (this.unreadMessages = e));
  }
}
