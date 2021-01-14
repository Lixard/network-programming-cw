import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { ChatListElement } from './models/chat.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-main-chat-page',
  templateUrl: './main-chat-page.component.html',
  styleUrls: ['./main-chat-page.component.css'],
})
export class MainChatPageComponent implements OnInit {
  chatListElements: ChatListElement[];

  constructor(private chatService: ChatService) {}

  ngOnInit(): void {
    this.chatService.getUserChatList().subscribe((e) => (this.chatListElements = e));
  }

  getChats(): Observable<ChatListElement[]> {
    return this.chatService.getUserChatList();
  }
}
