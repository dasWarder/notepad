import { Component, OnInit } from '@angular/core';
import {RedirectInterface} from "../../classes/redirectInterface";
import {NoteService} from "../../services/note/note.service";
import {ActivatedRoute} from "@angular/router";
import {Note} from "../../classes/note";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {

  notes: Array<Note> = [];

  constructor(private noteService: NoteService, private activatedRouter: ActivatedRoute) { }

  ngOnInit(): void {
    this.getNotesByDate();
  }

  getNotesByDate = () => {
    let date = this.activatedRouter.snapshot.params['date'];
    this.noteService.getNotesByDate(date).subscribe({
      next: value => {
        console.log(`In NotesComponent.getNotesByDate - Received notes = ${value} for date = ${date}`);
        this.notes = value;
      },
      error: err => console.error(`In NotesComponent.getNotesByDate - An exception occurred ${err.message}`)
    })
  }

}
