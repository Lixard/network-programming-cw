import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-chat-nav-element',
  templateUrl: './chat-nav-element.component.html',
  styleUrls: ['./chat-nav-element.component.css'],
})
export class ChatNavElementComponent implements OnInit {
  @Input() chat: Chat;
  unreadMessages$: Observable<number>;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.unreadMessages$ = this.chatService.getCountUnreadMessagesByChatId(this.chat.id);
  }
}
