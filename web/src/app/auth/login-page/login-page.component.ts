import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { switchMap } from 'rxjs/operators';
import { LoginData } from '../../models/login-data.model';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  form: FormGroup;

  loginData: LoginData;
  hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private auth: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.buildForm();
  }

  login(form: LoginData) {
    this.auth
      .login({
        username: form.username,
        password: form.password,
      })
      .pipe(switchMap(() => this.auth.loadProfile()))
      .subscribe(
        (profile) => {
          this.auth.user$.next(profile);
          this.router.navigateByUrl('/chat');
        },
        () => {
          this.form.setErrors({
            server: true,
          });
          this.form.controls.username.reset();
          this.form.controls.username.setErrors({
            'login-incorrect': true,
          });
          this.form.controls.password.reset();
          this.form.controls.password.setErrors({
            'login-incorrect': true,
          });
        },
      );
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      username: this.formBuilder.control(undefined, [Validators.required]),
      password: this.formBuilder.control(undefined, [Validators.required]),
    });
  }
}
