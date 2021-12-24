import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {TodayBarComponent} from "./components/today-bar/today-bar.component";
import {TagsComponent} from "./components/tags/tags.component";
import {CalendarComponent} from "./components/calendar/calendar.component";

const routes: Routes = [
  { path: 'home', component: MainPageComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'today', component: TodayBarComponent },
  { path: 'tags/:tag', component: TagsComponent },
  { path: 'calendar', component: CalendarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
