import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message, MessageSend } from '../models/message.model';
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

  public saveMessageAndFile(message: MessageSend, files: File[]): Observable<Message> {
    const formData = new FormData();

    const firstPart = new Blob([JSON.stringify(message)], { type: 'application/json' });

    formData.append('json', firstPart);

    if (files) {
      files.map((file) => formData.append('files', file, file.name));
    }

    return this.http.post<Message>(`/api/messages`, formData);
  }
}
