import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Note} from "../../classes/note";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  private baseUrl: string = 'http://localhost:8080/api/v1/notes';

  constructor(private httpClient: HttpClient) { }

  getTodayNotes() {
    return this.httpClient.get<Array<Note>>(`${this.baseUrl}/note`);
  }
}
