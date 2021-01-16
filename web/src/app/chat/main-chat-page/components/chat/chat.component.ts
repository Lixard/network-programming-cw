import { Component, Input, OnInit } from '@angular/core';
import { Chat } from '../../../../models/chat.model';
import { Observable } from 'rxjs';
import { Message } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  @Input() chat: Chat;
  form: FormGroup;

  messages$: Observable<Message[]>;

  constructor(private chatService: ChatService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.buildForm();
    this.messages$ = this.chatService.getChatMessages(this.chat.id);
  }

  private buildForm() {
    this.form = this.fb.group({
      message: this.fb.control('', Validators.required),
    });
  }
}
