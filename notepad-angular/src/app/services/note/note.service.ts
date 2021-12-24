import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
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
    return this.httpClient.post<Note>(this.baseUrl, note);
  }

  deleteNoteById(id: number) {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }

  getNoteById(id: number) {
    return this.httpClient.get<Note>(`${this.baseUrl}/${id}`);
  }

  getNotesByTag(tag: string) {
    return this.httpClient.get<Array<Note>>(this.baseUrl, {
      params: { 'tag': tag }
    });
  }

  getNotesByDate(date: string) {
    return this.httpClient.get<Array<Note>>(this.baseUrl, {
      params: { 'date': date }
    });
  }

  updateNoteById(id: number, note: Note) {
    return this.httpClient.put(`${this.baseUrl}/${id}`, note);
  }
}
