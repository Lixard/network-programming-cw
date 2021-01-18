import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { Chat } from '../../models/chat.model';
import { Observable } from 'rxjs';
import { ChatCreateDialogComponent } from './components/_dialogs/chat-create-dialog/chat-create-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-main-chat-page',
  templateUrl: './main-chat-page.component.html',
  styleUrls: ['./main-chat-page.component.scss'],
})
export class MainChatPageComponent implements OnInit {
  chatListElements: Chat[];
  selectedChat: Chat;

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.chatService.getUserChatList().subscribe((e) => (this.chatListElements = e));
  }

  getChats(): Observable<Chat[]> {
    return this.chatService.getUserChatList();
  }

  createChat() {
    const dialogRef = this.dialog.open(ChatCreateDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(() => {
      this.chatService.getUserChatList().subscribe((e) => (this.chatListElements = e));
    });
  }

  selectChat(chat: Chat) {
    this.selectedChat = chat;
    console.log(this.selectedChat);
    this.messageService.markChatMessagesAsRead(chat.id).subscribe();
  }
}
