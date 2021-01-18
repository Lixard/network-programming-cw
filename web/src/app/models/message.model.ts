import { User } from './user.model';

export interface Message {
  id: number;
  content: string;
  sender: User;
  sendDate: string;
}

export interface MessageSend {
  chatId: number;
  content: string;
}
