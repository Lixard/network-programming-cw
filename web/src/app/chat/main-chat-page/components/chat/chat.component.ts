import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { Message, MessageSend } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from '../../../../services/message.service';
import * as moment from 'moment';
import { AuthService } from '../../../../services/auth.service';
import { CurrentUser } from '../../../../models/user.model';
import { SocketClientService } from '../../../../services/socket-client.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit, OnChanges {
  @Input() chat: Chat;
  form: FormGroup;

  currentUser: CurrentUser;
  messages: Message[] = [];

  wsSub: Subscription;

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private authService: AuthService,
    private socketClient: SocketClientService,
  ) {
  }

  ngOnInit(): void {
    this.buildForm();
    this.chatService.getChatMessages(this.chat.id).subscribe((m) => (this.messages = m));
    this.authService.user$.subscribe((u) => (this.currentUser = u));
    this.wsSub = this.socketClient.onMessage(`/topic/chats/${this.chat.id}`)
      .subscribe((message: Message) => {
        console.log(message);
        this.messages.push(message);
      });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.wsSub) {
      this.wsSub.unsubscribe();
      this.ngOnInit();
    }
  }

  addFile() {
  }

  send(form) {
    const message: MessageSend = {
      chatId: this.chat.id,
      content: form.message,
    } as MessageSend;

    // this.socketClient.send('/ws/messages', message);
    this.messageService.sendMessage(message).subscribe();
    this.form.get('message').setValue('');
  }

  getSendDate(date: string): string {
    let momentDate = moment.utc(date);
    momentDate.locale('ru');
    return momentDate.utcOffset('+03:00').format('llll');
  }

  getCssStyleForMessage(message: Message): string {
    return this.currentUser.id === message.sender.id ? 'my-message' : '';
  }

  private buildForm() {
    this.form = this.fb.group({
      message: this.fb.control('', Validators.required),
    });
  }
}
