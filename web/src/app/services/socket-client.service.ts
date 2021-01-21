import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Client, Message, over } from 'stompjs';
import * as SockJS from 'sockjs-client';
import { SocketClientState } from '../models/socket-client-state.model';
import { filter, first, switchMap } from 'rxjs/operators';
import { StompSubscription } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class SocketClientService implements OnDestroy {
  private client: Client;
  private state: BehaviorSubject<SocketClientState>;

  constructor() {
    this.client = over(new SockJS('/api/socket'));
    this.state = new BehaviorSubject<SocketClientState>(SocketClientState.ATTEMPTING);
    this.client.connect({}, () => {
      this.state.next(SocketClientState.CONNECTED);
    });
  }

  static jsonHandler(message: Message): any {
    return JSON.parse(message.body);
  }

  static textHandler(message: Message): string {
    return message.body;
  }

  connect(): Observable<Client> {
    return new Observable<Client>((observer) => {
      this.state.pipe(filter((state) => state === SocketClientState.CONNECTED)).subscribe(() => {
        observer.next(this.client);
      });
    });
  }

  ngOnDestroy() {
    this.connect()
      .pipe(first())
      .subscribe((inst) => inst.disconnect(null));
  }

  onMessage(topic: string, handler = SocketClientService.jsonHandler): Observable<any> {
    return this.connect().pipe(
      first(),
      switchMap((inst) => {
        return new Observable<any>((observer) => {
          const subscription: StompSubscription = inst.subscribe(topic, (message) => {
            observer.next(handler(message));
          });
          return () => inst.unsubscribe(subscription.id);
        });
      }),
    );
  }

  onPlainMessage(topic: string): Observable<string> {
    return this.onMessage(topic, SocketClientService.textHandler);
  }

  send(topic: string, payload: any): void {
    this.connect()
      .pipe(first())
      .subscribe((inst) => inst.send(topic, {}, JSON.stringify(payload)));
  }
}
