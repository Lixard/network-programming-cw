import { ProfilePicture } from './profile-picture';

export interface UserForm {
  username: string;
  password: string;
}

export interface User {
  id: number;
  username: string;
  picture: ProfilePicture;
}

export interface AuthenticatedUser {
  authenticated: true;
  username: string;
  id: number;
}

export interface AnonymousUser {
  authenticated: false;
  username: null;
  id: null;
}

export type CurrentUser = AuthenticatedUser | AnonymousUser;
