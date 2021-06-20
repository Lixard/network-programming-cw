import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../../services/auth.service';
import { switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Chat } from '../../../../models/chat.model';
import { ChatService } from '../../../../services/chat.service';
import { MatDialog } from '@angular/material/dialog';
import { UserProfileDialogComponent } from '../_dialogs/user-profile-dialog/user-profile-dialog.component';
import { UserService } from '../../../../services/user.service';
import { User } from '../../../../models/user.model';
import { ProfilePicture } from '../../../../models/profile-picture';

@Component({
  selector: 'app-main-toolbar',
  templateUrl: './main-toolbar.component.html',
  styleUrls: ['./main-toolbar.component.scss'],
})
export class MainToolbarComponent implements OnInit {
  @Input() chat: Chat;

  user: User;

  constructor(
    public auth: AuthService,
    private userService: UserService,
    private chatService: ChatService,
    private router: Router,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.auth.user$
      .pipe(switchMap((cu) => this.userService.getUser(cu.id)))
      .subscribe((usr) => (this.user = usr));
  }

  logout(): void {
    this.auth.user$.subscribe((user) => console.log(user));
    this.auth
      .logout()
      .pipe(switchMap(() => this.auth.loadProfile()))
      .subscribe((user) => {
        this.auth.user$.next(user);
        this.router.navigateByUrl('/login');
      });
  }

  deleteChat() {
    this.chatService.removeChat(this.chat.id).subscribe(() => (this.chat = null));
  }

  updatePicture() {
    this.dialog.open(UserProfileDialogComponent, {
      height: '200px',
      width: '300px',
    });
  }

  getPictureSource(picture: ProfilePicture): string {
    return picture
      ? `data:${picture.type};base64,${picture.data}`
      : 'https://moonvillageassociation.org/wp-content/uploads/2018/06/default-profile-picture1.jpg';
  }
}
