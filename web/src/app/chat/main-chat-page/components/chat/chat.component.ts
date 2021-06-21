import {
  AfterViewChecked,
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
import { MatDialog } from '@angular/material/dialog';
import { Chat } from '../../../../models/chat.model';
import { Message, MessageSend } from '../../../../models/message.model';
import { ChatService } from '../../../../services/chat.service';
import { MessageService } from '../../../../services/message.service';
import { AuthService } from '../../../../services/auth.service';
import { CurrentUser } from '../../../../models/user.model';
import { SocketClientService } from '../../../../services/socket-client.service';
import { ProfilePicture } from '../../../../models/profile-picture';
import { DragAndDropDialogComponent } from '../_dialogs/drag-and-drop-dialog/drag-and-drop-dialog.component';
import { FileInfoModel } from '../../../../models/file.model';
import { DownloadFileService } from '../../../../services/download-file.service';

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

  files: File[];

  constructor(
    private chatService: ChatService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private authService: AuthService,
    private socketClient: SocketClientService,
    private dialog: MatDialog,
    private downloadFileService: DownloadFileService,
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

  addFile() {
    this.dialog
      .open(DragAndDropDialogComponent)
      .afterClosed()
      .subscribe((files) => (this.files = files));
  }

  send(form) {
    const message: MessageSend = {
      chatId: this.chat.id,
      content: form.message,
    } as MessageSend;
    if (message.content.length <= 0) {
      return;
    }
    this.messageService.sendMessage(message).subscribe((message) => {
      this.files.forEach((file) => this.messageService.saveFile(message.id, file).subscribe());
      this.files = [];
    });
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

  onScroll() {
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

  getPictureSource(picture: ProfilePicture): string {
    return picture
      ? `data:${picture.type};base64,${picture.data}`
      : 'https://moonvillageassociation.org/wp-content/uploads/2018/06/default-profile-picture1.jpg';
  }

  downloadFile(messageId: number, file: FileInfoModel) {
    this.messageService
      .downloadFile(messageId, file.id)
      .subscribe((blob) => this.downloadFileService.downloadFile(blob, file.mimeType));
  }
}
