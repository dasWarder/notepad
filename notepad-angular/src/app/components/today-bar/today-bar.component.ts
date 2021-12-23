import {Component, OnInit} from '@angular/core';
import {NoteService} from "../../services/note/note.service";
import {Note} from "../../classes/note";
import {Router} from "@angular/router";
import {BaseNoteComponentImpl} from "../../classes/baseNoteComponentImpl";

@Component({
  selector: 'app-today-bar',
  templateUrl: './today-bar.component.html',
  styleUrls: ['./today-bar.component.scss']
})
export class TodayBarComponent extends BaseNoteComponentImpl implements OnInit {

  notes: Array<Note> = [];
  todayDate: Date = new Date();
  note: Note = new Note();

  isCreatePanelOpen: boolean = false;
  iterator: number = 0
  tag: string = '';


  constructor(noteService: NoteService, router: Router) {
    super(noteService, router);
  }

  ngOnInit(): void {
    this.note.actualFor = new Date().toISOString().slice(0, 10);
    this.getNotes();
  }

  saveNote = () => {
    this.noteService.saveNote(this.note).subscribe({
      next: value => {
        console.log(`In TodayBarComponent.saveNote - A note = ${value} was created`);
        this.getNotes();
        this.resetNote();
      },
      error: err => console.error(`In TodayBarComponent.saveNote - An exception occurred ${err.message}`)
    });
  }

  override deleteNoteById = (id: number) : void => {
    super.deleteNoteById(id);
    this.getNotes();
  }

  getNotes = (): void => {
    this.noteService.getTodayNotes().subscribe({
      next: value => this.notes = value,
      error: err => console.error(err)
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

  override redirect = (tag: string): void => {
    super.redirect(`tags`, tag);
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

  private resetNote = () => {
    this.note = new Note();
    this.iterator = 0;
    this.note.actualFor = new Date().toISOString().slice(0, 10);
  }
}
