import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Note} from "../../classes/note";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private baseUrl: string = 'http://localhost:8080/api/v1/notes';

  constructor(private httpClient: HttpClient) { }

  getTodayNotes() {
    return this.httpClient.get<Array<Note>>(`${this.baseUrl}/note`);
  }

  saveNote(note: Note) {
    return this.httpClient.post<Observable<Note>>(`${this.baseUrl}`, note);
  }

  deleteNoteById(id: number) {
    return this.httpClient.delete<Observable<void>>(`${this.baseUrl}/${id}`);
  }

  getNoteById(id: number) {
    return this.httpClient.get<Observable<Note>>(`${this.baseUrl}/${id}`);
  }
}
