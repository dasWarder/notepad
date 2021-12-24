import {Component} from '@angular/core';
import {NoteService} from "../../services/note/note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Note} from "../../classes/note";
import {TodayBarComponent} from "../today-bar/today-bar.component";
import {NgbPopoverConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent extends TodayBarComponent {

  override notes: Array<Note> = [];
  date!: Date;

  constructor(noteService: NoteService, router: Router, popConfig: NgbPopoverConfig, private activatedRouter: ActivatedRoute) {
    super(noteService, router, popConfig);
    this.date = new Date(this.getDate());
    this.note.actualFor = this.getDate();
  }

  override ngOnInit(): void {
    this.getNotes();
  }

  override getNotes = (): void => {

    let date = this.getDate();

    this.noteService.getNotesByDate(date).subscribe({
      next: value => {
        console.log(`In NotesComponent.getNotesByDate - Received notes = ${value} for date = ${date}`);
        this.notes = value;
      },
      error: err => console.error(`In NotesComponent.getNotesByDate - An exception occurred ${err.message}`)
    })
  }

  private getDate = (): string => {
    return this.activatedRouter.snapshot.params['date'];
  }

  dateGreaterOrEqualsThatToday = (): boolean => {

    let curDate = this.date;
    let today = new Date(this.todayDate.getFullYear(), this.todayDate.getMonth(), this.todayDate.getDate());

    return curDate >= today;
  }

}
