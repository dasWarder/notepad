import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Note} from "../../classes/note";
import {NoteService} from "../../services/note/note.service";
import {BaseNoteComponentImpl} from "../../classes/baseNoteComponentImpl";

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.scss']
})
export class TagsComponent extends BaseNoteComponentImpl implements OnInit {

  tag: string = '';
  notes: Array<Note> = [];
  todayDate: string = new Date().toISOString().slice(0, 10);

  constructor(noteService: NoteService, router: Router, private activatedRouter: ActivatedRoute) {
    super(noteService, router);
    this.tag = this.activatedRouter.snapshot.params['tag'];
  }

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes = () => {
    this.noteService.getNotesByTag(this.tag).subscribe({
      next: value => {
        console.log(`In TagsComponent.getNotesByTag - Notes by tag = ${this.tag} received`);
        this.notes = value;
      },
      error: err => console.error(`In TagsComponent.getNotesByTag - An exception occurred ${err.message}`)
    })
  }

  deleteNote = (id: number) => {
    super.deleteNoteById(id);
  }

  override redirect = (tag: string): void => {
    super.redirect(`redirect`, tag);
  }


  checkActivityOfActualDate = (actualFor: string) : boolean => {

    let today = new Date(this.todayDate).getDate();
    let active = new Date(actualFor).getDate();

    return today < active;
  }

}
