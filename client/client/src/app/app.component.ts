import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { hasLifecycleHook } from '@angular/compiler/src/lifecycle_reflector';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private http:HttpClient;
  title = 'client';
  inputText : string;
  myControl = new FormControl();
  options: string[] = ['One', 'Two', 'Three','only'];
  filteredOptions: Observable<string[]>;
  movies;
  movieOption;

 /* constructor(){
    this.movies = this.getMovies();
  }*/

  ngOnInit() {

    /*this.movies.subscribe(result => {
      this.movieOption=result;
    })*/
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );

  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  public demo( value): void{
    console.log("here"+value);
    //make call to backend to retrieve the data. 
  }

  /*getMovies() : Observable<any[]> {

    return hasLifecycleHook;
    //return this.http.post<any[]>("url","data");
  }*/
  

}


