import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChatListElement } from '../chat/main-chat-page/models/chat.model';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  constructor(private http: HttpClient) {}

  public getUserChatList(): Observable<ChatListElement[]> {
    return this.http.get<ChatListElement[]>(`api/chats`);
  }

  public getCountUnreadMessagesByChatId(chatId: number): Observable<number> {
    return this.http.get<number>(`api/chats/${chatId}/unread`);
  }
}
