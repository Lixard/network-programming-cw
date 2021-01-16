import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../../../models/user.model';
import { MatChipInputEvent } from '@angular/material/chips';
import { UserService } from '../../../../../services/user.service';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Observable } from 'rxjs';
import { MatAutocomplete, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { map, startWith } from 'rxjs/operators';
import { ChatCreate } from '../../../../../models/chat.model';
import { ChatService } from '../../../../../services/chat.service';

@Component({
  selector: 'app-chat-create-dialog',
  templateUrl: './chat-create-dialog.component.html',
  styleUrls: ['./chat-create-dialog.component.css'],
})
export class ChatCreateDialogComponent implements OnInit {
  form: FormGroup;
  users: User[];
  selectedUsers: User[] = [];
  filteredUsers: Observable<User[]>;

  @ViewChild('usersInput') inputElement: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  separatorKeysCodes: number[] = [ENTER, COMMA];

  constructor(
    private dialogRef: MatDialogRef<ChatCreateDialogComponent>,
    private fb: FormBuilder,
    private userService: UserService,
    private chatService: ChatService,
    @Inject(MAT_DIALOG_DATA) public data,
  ) {
    this.buildForm();
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((u) => {
      this.users = u;
      this.filteredUsers = this.form.get('users').valueChanges.pipe(
        startWith(''),
        map((user: string) => this.filter(user)),
      );
    });
  }

  createChat(form) {
    const chat: ChatCreate = {
      name: form.name,
      participationIds: this.selectedUsers.map((u) => u.id),
    } as ChatCreate;

    console.log(chat);
    this.chatService.createChat(chat).subscribe();
    this.dialogRef.close();
  }

  buildForm() {
    this.form = this.fb.group({
      name: this.fb.control('', Validators.required),
      users: this.fb.control(''),
    });
  }

  removeChip(user: User) {
    const index = this.selectedUsers.indexOf(user);
    if (index >= 0) {
      this.selectedUsers.splice(index, 1);
    }
    console.log(this.selectedUsers);
  }

  addChip($event: MatChipInputEvent) {
    const input = $event.input;
    const value = $event.value;
    console.log('value = ', value);

    if ((value || '').trim()) {
      const user: User = this.users.find((u) => u.username === value);
      console.log('found user = ', user);
      if (user) {
        this.selectedUsers.push(user);
      }
    }

    if (input) {
      input.value = '';
    }
    this.form.get('users').setValue('');
    console.log(this.selectedUsers);
  }

  getOptionText(option) {
    return option ? option.username : null;
  }

  autocompleteSelected($event: MatAutocompleteSelectedEvent) {
    this.selectedUsers.push(this.users.find((u) => u.username === $event.option.viewValue));
    this.inputElement.nativeElement.value = '';
    this.form.get('users').setValue('');
  }

  private filter(value: string): User[] {
    const filterValue = value.toLowerCase();
    return this.users
      .filter((u) => u.username.toLowerCase().indexOf(filterValue) === 0)
      .filter((u) => !this.selectedUsers.includes(u));
  }
}
