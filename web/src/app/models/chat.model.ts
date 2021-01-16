import { User } from './user.model';

export interface ChatCreate {
  name: string;
  participationIds: number[];
}

export interface Chat {
  id: number;
  name: string;
  participations: User[];
}
