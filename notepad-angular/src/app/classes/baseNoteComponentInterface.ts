import {RedirectInterface} from "./redirectInterface";

export interface BaseNoteComponentInterface extends RedirectInterface {

  deleteNoteById(id: number) : void;

  getNotes(): void;

}
