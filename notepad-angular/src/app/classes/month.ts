export class Month {

  name!: string;
  days: Array<Date> = [];

  constructor(name: string, days: Array<Date>) {
    this.name = name;
    this.days = days;
  }
}
