import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message, MessageSend } from '../models/message.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor(private http: HttpClient) {}

  public sendMessage(message: MessageSend): Observable<Message> {
    return this.http.post<Message>(`/api/messages`, message);
  }
}
