

export class Note {

  id!: number;
  description: string = "";
  addedAt!: Date;
  actualFor!: string;
  reminder: boolean = false;
  tags: Array<string> = [];

  constructor() {
  }
}
