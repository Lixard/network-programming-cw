import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-chat-create-dialog',
  templateUrl: './chat-create-dialog.component.html',
  styleUrls: ['./chat-create-dialog.component.css'],
})
export class ChatCreateDialogComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<ChatCreateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data,
  ) {}

  ngOnInit(): void {
    this.dialogRef.updatePosition({ top: '50%', left: '50%' });
  }

  private buildForm() {}
}
