import {Component, OnInit} from '@angular/core';
import {Month} from "../../classes/month";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {

  date: Date = new Date();
  year: Array<Month> = [];
  monthIndex: number = 0;

  constructor() {
    this.populateMonth();
  }

  ngOnInit(): void {
  }


  setMonth = (index: number) => {

  }

  private populateMonth = () => {

    let next = new Date(this.date.getFullYear(), this.date.getMonth());

    for(let i = 0; i < 12; i++) {
      let days: Array<Date> = this.getMonthDates(next);
      this.year[i] = new Month(next.toLocaleString('en-us', { month: 'long' }), days);
      next = new Date(next);
      next.setMonth(next.getMonth() + 1);
    }
  }

  private getMonthDates = (date: Date): Array<Date> => {
    let days: Array<Date> = [];
    let day = new Date(date);

    while (day.getMonth() === date.getMonth()) {
      days.push(day);
      day = new Date(day);
      day.setDate(day.getDate() + 1);
    }

    return days;
  }

  setMonthIndex = (index: number) => {
    this.monthIndex = index;
  }

}
