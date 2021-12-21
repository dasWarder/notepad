import { Component, OnInit } from '@angular/core';
import {faBars, faHome} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  home = faHome;
  bar = faBars;

  constructor() { }

  ngOnInit(): void {
  }

}
