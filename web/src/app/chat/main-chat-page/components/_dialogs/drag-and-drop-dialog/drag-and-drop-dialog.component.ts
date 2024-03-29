import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-drag-and-drop-dialog',
  templateUrl: './drag-and-drop-dialog.component.html',
  styleUrls: ['./drag-and-drop-dialog.component.css'],
})
export class DragAndDropDialogComponent implements OnInit, OnDestroy {
  files: File[] = [];

  constructor(
    private dialogRef: MatDialogRef<DragAndDropDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {}

  ngOnInit(): void {
    this.files = this.data;
  }

  ngOnDestroy(): void {
    this.dialogRef.close(this.files);
  }

  fileBrowseHandler(files: FileList) {
    this.prepareFilesList(Array.from(files));
  }

  onFileDropped($event: any) {
    this.prepareFilesList($event);
  }

  prepareFilesList(files: Array<File>) {
    for (const item of files) {
      this.files.push(item);
    }
  }

  deleteFile(index: any) {
    this.files.splice(index, 1);
  }

  formatBytes(bytes, decimals = 2) {
    if (bytes === 0) {
      return '0 Bytes';
    }
    const k = 1024;
    const dm = decimals <= 0 ? 0 : decimals;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  }
}
