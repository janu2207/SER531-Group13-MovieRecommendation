import {Component, OnInit, SystemJsNgModuleLoader} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  title = 'client';
  inputText : string;
  myControl = new FormControl();
  options: string[] = ['One', 'Two', 'Three','only'];
  filteredOptions: Observable<string[]>;
  movies;
  movieOption;
  suggestedMovies;
  displayMovies;
  
  

 constructor(private http: HttpClient){
    this.movies = this.getMovies();
  }

  ngOnInit() {

    this.movies.subscribe(result => {
     this.movieOption=new Array<String>();
    
      result.forEach(element => {
        this.movieOption.push(element.id);
      });

      console.log(this.movieOption);
     
      this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    })
  
    

  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.movieOption.filter(option => option.toLowerCase().includes(filterValue));
  }

  public demo( value): void{
    console.log("here"+value);
    this.suggestedMovies = this.getSuggestedMovies();
    this.suggestedMovies.subscribe(result =>{
      this.displayMovies=result;
      console.log(this.displayMovies);
    }) 
    
  }

  getSuggestedMovies() : Observable<any[]>{
    return this.http.get<any[]>("http://localhost:3000/moviedetails");
  }

  getMovies() : Observable<any[]> {
    return this.http.get<any[]>("http://localhost:3000/movies");
  }
  

}


