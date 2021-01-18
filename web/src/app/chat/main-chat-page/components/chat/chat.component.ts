import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { Message, MessageSend } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from '../../../../services/message.service';
import * as moment from 'moment';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit, OnChanges {
  @Input() chat: Chat;
  form: FormGroup;

  messages: Message[] = [];

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private fb: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.buildForm();
    this.chatService.getChatMessages(this.chat.id).subscribe((m) => (this.messages = m));
  }

  ngOnChanges(changes: SimpleChanges) {
    this.ngOnInit();
  }

  addFile() {}

  send(form) {
    const message: MessageSend = {
      chatId: this.chat.id,
      content: form.message,
    } as MessageSend;

    this.messageService.sendMessage(message).subscribe((m) => console.log('message send = ', m));
    this.form.get('message').setValue('');
  }

  getSendDate(date: string): string {
    let momentDate = moment.utc(date);
    momentDate.locale('ru');
    return momentDate.format('llll');
  }

  private buildForm() {
    this.form = this.fb.group({
      message: this.fb.control('', Validators.required),
    });
  }
}
