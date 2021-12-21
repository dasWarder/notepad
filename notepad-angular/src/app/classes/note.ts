

export class Note {

  id!: number;
  description!: string;
  addedAt!: Date;
  actualFor!: Date;
  reminder!: boolean;
  tags!: Array<String>;

  constructor() {
  }
}
