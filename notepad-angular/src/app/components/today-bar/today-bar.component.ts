import { Component, OnInit } from '@angular/core';
import {faPlus} from "@fortawesome/free-solid-svg-icons/faPlus";
import {NoteService} from "../../services/note/note.service";
import {Note} from "../../classes/note";
import {faTag} from "@fortawesome/free-solid-svg-icons/faTag";
import {faThumbtack} from "@fortawesome/free-solid-svg-icons/faThumbtack";
import {faClock} from "@fortawesome/free-solid-svg-icons/faClock";
import {faStickyNote} from "@fortawesome/free-solid-svg-icons/faStickyNote";

@Component({
  selector: 'app-today-bar',
  templateUrl: './today-bar.component.html',
  styleUrls: ['./today-bar.component.scss']
})
export class TodayBarComponent implements OnInit {

  alarmClockIcon = faClock;
  noteIcon = faThumbtack;
  addIcon = faPlus;
  tagIcon = faTag;
  emptyIcon = faStickyNote;
  notes: Array<Note> = [];
  todayDate: Date = new Date();

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
    this.noteService.getTodayNotes().subscribe({
      next: value => this.notes = value,
      error: err => console.error(err)
    });
  }
}
