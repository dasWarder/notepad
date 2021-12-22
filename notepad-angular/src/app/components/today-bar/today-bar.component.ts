import {Component, OnInit} from '@angular/core';
import {faPlus} from "@fortawesome/free-solid-svg-icons/faPlus";
import {NoteService} from "../../services/note/note.service";
import {Note} from "../../classes/note";
import {faTag} from "@fortawesome/free-solid-svg-icons/faTag";
import {faThumbtack} from "@fortawesome/free-solid-svg-icons/faThumbtack";
import {faClock} from "@fortawesome/free-solid-svg-icons/faClock";
import {faStickyNote} from "@fortawesome/free-solid-svg-icons/faStickyNote";
import {faEdit} from "@fortawesome/free-solid-svg-icons/faEdit";
import {faTrash} from "@fortawesome/free-solid-svg-icons/faTrash";
import {Router} from "@angular/router";

@Component({
  selector: 'app-today-bar',
  templateUrl: './today-bar.component.html',
  styleUrls: ['./today-bar.component.scss']
})
export class TodayBarComponent implements OnInit {

  editIcon = faEdit;
  deleteIcon = faTrash;
  alarmClockIcon = faClock;
  noteIcon = faThumbtack;
  addIcon = faPlus;
  tagIcon = faTag;
  emptyIcon = faStickyNote;

  notes: Array<Note> = [];
  todayDate: Date = new Date();
  note: Note = new Note();

  isCreatePanelOpen: boolean = false;
  iterator: number = 0
  tag: string = '';


  constructor(private noteService: NoteService, private router: Router) {
  }

  ngOnInit(): void {
    this.note.actualFor = new Date().toISOString().slice(0, 10);
    this.getTodayNotes();
  }

  saveNote = () => {
    this.noteService.saveNote(this.note).subscribe({
      next: value => {
        console.log(`In TodayBarComponent.saveNote - A note = ${value} was created`);
        this.getTodayNotes();
        this.resetNote()
      },
      error: err => console.error(`In TodayBarComponent.saveNote - An exception occurred`)
    });
  }

  deleteNote = (id: number) => {
    this.noteService.deleteNoteById(id).subscribe({
      next: value => {
        console.log(`In TodayBarComponent.deleteNote - A note were deleted by id = ${id}`);
        this.getTodayNotes()
      },
      error: err => console.error(`In TodayBarComponent.deleteNote - An exception occurred ${err.message}`)
    });
  }

  deleteTag = (tag: string) => {

    this.note.tags.forEach((e, i) => {
      if (e === tag) {
        this.note.tags.splice(i, 1);
        this.rebaseArray(i);
      }
    })
  }

  addTag = () => {
    if (!this.isTagInArray(this.tag) && this.tag != '') {
      this.note.tags[this.iterator] = this.tag;
      this.iterator++;
      this.tag = "";
    }
  }

  private rebaseArray = (idx: number): void => {

    let newArr: Array<string> = [];
    const size = this.note.tags.length;

    for (let i = 0; i < size; i++) {
      if (i < idx) {
        newArr[i] = this.note.tags[i];
      } else {
        newArr[i] = this.note.tags[i + 1];
      }
    }

    this.note.tags = newArr;
    this.iterator--;
  }

  private isTagInArray = (tag: string): boolean => {
    let numberOfDuplicates = this.note.tags.filter(t => t === tag).length;
    return numberOfDuplicates > 0;
  }

  private getTodayNotes = () => {
    this.noteService.getTodayNotes().subscribe({
      next: value => this.notes = value,
      error: err => console.error(err)
    });
  }

  private resetNote = () => {
    this.note = new Note();
    this.iterator = 0;
    this.note.actualFor = new Date().toISOString().slice(0, 10);
  }
}
