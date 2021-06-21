export interface FileInfoModel {
  id: number;
  messageId: number;
  name: string;
  mimeType: string;
  size: number;
}

export interface FileDataModel {
  id: number;
  name: string;
  mimeType: string;
  data: string;
}
