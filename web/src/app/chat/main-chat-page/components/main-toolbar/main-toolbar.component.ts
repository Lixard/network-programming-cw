import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../../services/auth.service';
import { switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-toolbar',
  templateUrl: './main-toolbar.component.html',
  styleUrls: ['./main-toolbar.component.css'],
})
export class MainToolbarComponent implements OnInit {
  constructor(public auth: AuthService, private router: Router) {}

  ngOnInit(): void {}

  logout(): void {
    this.auth.user$.subscribe((user) => console.log(user));
    this.auth
      .logout()
      .pipe(switchMap(() => this.auth.loadProfile()))
      .subscribe((user) => {
        this.auth.user$.next(user);
        this.router.navigateByUrl('/login');
      });
  }
}
