import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Subscription } from 'rxjs';
import { Chat } from '../../../../models/chat.model';
import { Message, MessageSend } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';
import { MessageService } from '../../../../services/message.service';
import { AuthService } from '../../../../services/auth.service';
import { CurrentUser } from '../../../../models/user.model';
import { SocketClientService } from '../../../../services/socket-client.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit, OnChanges, AfterViewChecked {
  @Input() chat: Chat;

  form: FormGroup;

  @ViewChild('messageBox') messageBox: ElementRef;

  currentUser: CurrentUser;

  messages: Message[] = [];

  wsSub: Subscription;

  disableScrollDown = false;

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private authService: AuthService,
    private socketClient: SocketClientService,
  ) {}

  ngOnInit(): void {
    this.buildForm();
    this.chatService.getChatMessages(this.chat.id).subscribe((m) => (this.messages = m));
    this.authService.user$.subscribe((u) => (this.currentUser = u));
    this.wsSub = this.socketClient
      .onMessage(`/topic/chats/${this.chat.id}`)
      .subscribe((message: Message) => {
        console.log(message);
        this.messages.push(message);
        this.disableScrollDown = false;
      });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.wsSub) {
      this.wsSub.unsubscribe();
      this.ngOnInit();
      this.disableScrollDown = false;
    }
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  addFile() {}

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
    const momentDate = moment.utc(date);
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

  private onScroll() {
    const element = this.messageBox.nativeElement;
    const atBottom = element.scrollHeight - element.scrollTop === element.clientHeight;
    this.disableScrollDown = !(this.disableScrollDown && atBottom);
  }

  private scrollToBottom() {
    if (this.disableScrollDown) {
      return;
    }
    try {
      this.messageBox.nativeElement.scrollTop = this.messageBox.nativeElement.scrollHeight;
    } catch (err) {}
  }
}
