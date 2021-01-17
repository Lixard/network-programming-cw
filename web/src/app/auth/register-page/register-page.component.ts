import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserForm } from '../../models/user.model';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss'],
})
export class RegisterPageComponent implements OnInit {
  form: FormGroup;
  user: UserForm;

  hidePassword = true;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {
    this.buildForm();
  }

  ngOnInit(): void {
    this.form.reset();
  }

  register(form: UserForm) {
    this.user = ({
      username: form.username,
      password: form.password,
    } as unknown) as UserForm;
    this.auth.register(this.user).subscribe(
      () => {
        this.auth
          .login({
            username: form.username,
            password: form.password,
          })
          .pipe(switchMap(() => this.auth.loadProfile()))
          .subscribe(() => {
            this.auth.loadProfile().subscribe((profile) => {
              this.auth.user$.next(profile);
              this.router.navigateByUrl(`/chat`);
            });
          });
      },
      () => {
        this.form.controls.username.setErrors({
          'unknown-error': true,
        });
      },
    );
  }

  private buildForm(): void {
    this.form = this.fb.group({
      username: this.fb.control('', [Validators.required, Validators.maxLength(50)]),
      password: this.fb.control('', [Validators.required, Validators.minLength(8)]),
    });
  }
}
