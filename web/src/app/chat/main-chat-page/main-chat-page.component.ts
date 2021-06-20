import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { Chat } from '../../models/chat.model';
import { Observable, Subject } from 'rxjs';
import { ChatCreateDialogComponent } from './components/_dialogs/chat-create-dialog/chat-create-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MessageService } from '../../services/message.service';
import { AuthService } from '../../services/auth.service';
import { SocketClientService } from '../../services/socket-client.service';

@Component({
  selector: 'app-main-chat-page',
  templateUrl: './main-chat-page.component.html',
  styleUrls: ['./main-chat-page.component.scss'],
})
export class MainChatPageComponent implements OnInit {
  chatListElements: Chat[];
  selectedChat: Chat;

  chatNavEvent: Subject<void> = new Subject<void>();

  constructor(
    private authService: AuthService,
    private chatService: ChatService,
    private messageService: MessageService,
    private dialog: MatDialog,
    private socketClient: SocketClientService,
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe(user => {
      this.socketClient
        .onMessage(`/topic/users/${user.id}/chat-create`)
        .subscribe((chat: Chat) => this.chatListElements.push(chat));

      this.socketClient
        .onMessage(`/topic/users/${user.id}/chat-remove`)
        .subscribe((chatId: number) => this.chatListElements = this.chatListElements.filter((value) => value.id !== chatId));
    });
    this.chatService.getUserChatList().subscribe((e) => (this.chatListElements = e));
  }

  getChats(): Observable<Chat[]> {
    return this.chatService.getUserChatList();
  }

  createChat() {
    const dialogRef = this.dialog.open(ChatCreateDialogComponent, {
      width: '400px',
    });
  }

  selectChat(chat: Chat) {
    this.selectedChat = chat;
    this.messageService.markChatMessagesAsRead(chat.id).subscribe(() => {
      this.chatNavEvent.next();
    });
  }
}
