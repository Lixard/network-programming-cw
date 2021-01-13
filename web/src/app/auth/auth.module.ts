import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { LOAD_CURRENT_USER_INITIALIZER } from '../services/auth.service';
import { AuthRoutingModule } from './auth-routing.module';
import { RouterModule } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [LoginPageComponent, RegisterPageComponent],
  imports: [
    RouterModule,
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    MatInputModule,
    FlexLayoutModule,
  ],
  providers: [LOAD_CURRENT_USER_INITIALIZER],
})
export class AuthModule {
}
