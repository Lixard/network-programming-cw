import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DownloadFileService {
  constructor() {}

  downloadFile(data: any, type: string) {
    let blob = new Blob([data], { type: type });
    let url = window.URL.createObjectURL(blob);
    window.open(url);
  }
}
