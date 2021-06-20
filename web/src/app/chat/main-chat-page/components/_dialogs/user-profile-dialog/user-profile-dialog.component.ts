import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../../../services/user.service';
import { AuthService } from '../../../../../services/auth.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-user-profile-dialog',
  templateUrl: './user-profile-dialog.component.html',
  styleUrls: ['./user-profile-dialog.component.css'],
})
export class UserProfileDialogComponent implements OnInit {
  fileToUpload: File | null = null;

  currentUserId: number;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private dialogRef: MatDialogRef<UserProfileDialogComponent>,
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => (this.currentUserId = user.id));
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  upload() {
    this.userService.updateProfilePicture(this.currentUserId, this.fileToUpload).subscribe();
    this.dialogRef.close();
  }
}
