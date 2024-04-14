import { Component, OnInit } from '@angular/core';
import { BaseService } from '../base.service';

@Component({
  selector: 'app-surprise',
  templateUrl: './surprise.component.html',
  styleUrls: ['./surprise.component.css']
})
export class SurpriseComponent implements OnInit  {
  object:any={};
  arrayBook:any=[];
  thisBook:any={};
  emptyData = false;
  clicked = false;
  rand!: number;
  image:any="regény";
  constructor(private base: BaseService) { }

  ngOnInit (): void {
    this.clicked = false;
    this.initBooks();
  }

  initBooks() {
    this.base.getBooks().subscribe({
      next: (res) => {
        this.object=res;
        this.arrayBook=res;
        this.emptyData = false;
      },
      error: (err) => {
        console.log('Hiba az oldalon: ' + err);
        this.emptyData = true;
      }
    });
  }

  loadSurprise() {
    if (!this.clicked){ 
      this.clicked = true;
    }
    this.rand = this.getRandomInt(0, (this.arrayBook.length - 1));
    this.object=this.arrayBook[this.rand];
    this.image=this.object.category;
  }

  getRandomInt(min: number, max: number): number {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
}
