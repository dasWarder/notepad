import {Component, OnInit} from '@angular/core';
import {faBars, faHome} from '@fortawesome/free-solid-svg-icons'
import {faCalendarAlt} from "@fortawesome/free-solid-svg-icons/faCalendarAlt";
import {faCalendarDay} from "@fortawesome/free-solid-svg-icons/faCalendarDay";
import {faTags} from "@fortawesome/free-solid-svg-icons/faTags";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  home = faHome;
  bar = faBars;
  todayCalendar = faCalendarDay;
  furtherCalendar = faCalendarAlt;
  tags = faTags;
  isBarOpen: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
