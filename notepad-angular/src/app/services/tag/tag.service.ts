import {Injectable} from '@angular/core';
import {NoteService} from "../note/note.service";
import {Note} from "../../classes/note";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  notes: Array<Note> = [];

  constructor(private noteService: NoteService) {
  }

  getNotesByTagName(tagName: string): Array<Note> {

    this.noteService.getNotesByTag(tagName).subscribe({
      next: value => this.notes = value,
      error: err => console.error(err)
    })

    return this.notes;
  }
}
