import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './components/main/app.component';
import {NgbModule, NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from "@angular/forms";
import {HeaderComponent} from './components/header/header.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {MainPageComponent} from './components/main-page/main-page.component';
import {TodayBarComponent} from './components/today-bar/today-bar.component';
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import { TagsComponent } from './components/tags/tags.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { NotesComponent } from './components/notes/notes.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainPageComponent,
    TodayBarComponent,
    TagsComponent,
    CalendarComponent,
    NotesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    FontAwesomeModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
