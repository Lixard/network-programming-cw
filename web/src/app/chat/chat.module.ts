import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChatRoutingModule } from './chat-routing.module';
import { MainChatPageComponent } from './main-chat-page/main-chat-page.component';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSliderModule } from '@angular/material/slider';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MainToolbarComponent } from './main-chat-page/components/main-toolbar/main-toolbar.component';
import { RouterModule } from '@angular/router';
import { ChatNavElementComponent } from './main-chat-page/components/chat-nav-element/chat-nav-element.component';
import { MatBadgeModule } from '@angular/material/badge';
import { ChatCreateDialogComponent } from './main-chat-page/components/_dialogs/chat-create-dialog/chat-create-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { ChatComponent } from './main-chat-page/components/chat/chat.component';
import { UserProfileDialogComponent } from './main-chat-page/components/_dialogs/user-profile-dialog/user-profile-dialog.component';
import { DragAndDropDialogComponent } from './main-chat-page/components/_dialogs/drag-and-drop-dialog/drag-and-drop-dialog.component';
import { DndDirective } from './main-chat-page/components/_dialogs/drag-and-drop-dialog/directives/dnd.directive';

@NgModule({
  declarations: [
    MainChatPageComponent,
    MainToolbarComponent,
    ChatNavElementComponent,
    ChatCreateDialogComponent,
    ChatComponent,
    UserProfileDialogComponent,
    DragAndDropDialogComponent,
    DndDirective,
  ],
  imports: [
    CommonModule,
    ChatRoutingModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatSelectModule,
    MatButtonToggleModule,
    MatSlideToggleModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
    FlexLayoutModule,
    RouterModule,
    MatBadgeModule,
    ReactiveFormsModule,
    MatChipsModule,
  ],
})
export class ChatModule {}
