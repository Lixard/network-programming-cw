import { User } from './user.model';
import { FileInfoModel } from './file.model';

export interface Message {
  id: number;
  content: string;
  sender: User;
  sendDate: string;
  files: FileInfoModel[];
}

export interface MessageSend {
  chatId: number;
  content: string;
}
