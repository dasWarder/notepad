import {BaseNoteComponentInterface} from "./baseNoteComponentInterface";
import {NoteService} from "../services/note/note.service";
import {BaseIcon} from "./baseIcon";
import {Router} from "@angular/router";


export abstract class BaseNoteComponentImpl extends BaseIcon implements BaseNoteComponentInterface {

  protected constructor(protected noteService: NoteService, protected router: Router) {
    super();
  }

  deleteNoteById(id: number): void {
    this.noteService.deleteNoteById(id).subscribe({
      next: value => {
        console.log(`In TodayBarComponent.deleteNote - A note were deleted by id = ${id} with value = ${value}`);
        this.getNotes();
      },
      error: err => console.error(`In TodayBarComponent.deleteNote - An exception occurred ${err.message}`)
    });
  }

  abstract getNotes(): void;

  redirect(path: string, param?: string) : void {
    this.router.navigate([path, param]).catch(error => console.error(error));
  }

}
