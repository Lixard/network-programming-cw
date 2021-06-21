import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Message, MessageSend } from '../models/message.model';
import { Observable } from 'rxjs';
import { FileInfoModel } from '../models/file.model';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  constructor(private http: HttpClient) {}

  public sendMessage(message: MessageSend): Observable<Message> {
    return this.http.post<Message>(`/api/messages`, message);
  }

  public markChatMessagesAsRead(chatId: number): Observable<void> {
    return this.http.post<void>(`/api/chats/${chatId}/mark-as-read`, null);
  }

  public saveFile(messageId: number, file: File): Observable<FileInfoModel> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<FileInfoModel>(`/api/messages/${messageId}/files`, formData);
  }

  public downloadFile(messageId: number, fileId: number): Observable<any> {
    return this.http.get(`/api/messages/${messageId}/files/${fileId}`, { responseType: 'blob' });
  }
}
