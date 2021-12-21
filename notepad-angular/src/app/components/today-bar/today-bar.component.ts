import { Component, OnInit } from '@angular/core';
import {faPlus} from "@fortawesome/free-solid-svg-icons/faPlus";

@Component({
  selector: 'app-today-bar',
  templateUrl: './today-bar.component.html',
  styleUrls: ['./today-bar.component.scss']
})
export class TodayBarComponent implements OnInit {

  add = faPlus;

  constructor() { }

  ngOnInit(): void {
  }

}
