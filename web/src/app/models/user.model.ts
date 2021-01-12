export interface User {
  username: string;
  password: string;
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
