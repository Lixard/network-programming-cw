import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { Observable } from 'rxjs';
import { Message } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  @Input() chat: Chat;

  messages$: Observable<Message[]>;

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.messages$ = this.chatService.getChatMessages(this.chat.id);
  }
}
