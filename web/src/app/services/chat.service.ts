import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat, ChatCreate } from '../models/chat.model';
import { Message } from '../models/message.model';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  constructor(private http: HttpClient) {}

  public getUserChatList(): Observable<Chat[]> {
    return this.http.get<Chat[]>(`api/chats`);
  }

  public getCountUnreadMessagesByChatId(chatId: number): Observable<number> {
    return this.http.get<number>(`api/chats/${chatId}/unread`);
  }

  public createChat(chat: ChatCreate): Observable<Chat> {
    return this.http.post<Chat>('/api/chats', chat);
  }

  public getChatMessages(chatId: number): Observable<Message[]> {
    return this.http.get<Message[]>(`/api/chats/${chatId}/messages`);
  }
}
