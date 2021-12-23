export interface BaseNoteComponentInterface {

  deleteNoteById(id: number) : void;

  getNotes(): void;

  redirect(path:string, param?: string): void;

}
