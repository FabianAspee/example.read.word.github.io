import { HttpClient, HttpHeaders} from "@angular/common/http"; 
import data from "../../config/url_config.json";
import { Injectable } from "@angular/core";
import { catchError } from "rxjs/operators";
import { HandleError, HttpErrorHandler } from "src/app/config/http-error-handler.service";

@Injectable()
export class ReadDatabaseAsyncService{ 
    private handleError: HandleError;

    constructor (private http: HttpClient,
        httpErrorHandler: HttpErrorHandler) {
        this.handleError = httpErrorHandler.createHandleError('ReadDatabaseAsyncService');
    } 
   
    async readPlayListAsync():Promise<string>{
        return this.http.post<string>(data.read_database_async.url_read_database + data.read_database_async.controller_playlist+
            data.read_database_async.path_playlist.read_all_playlist, undefined, this.getHttpOptionsWithoutParam())
            .pipe(catchError(this.handleError<string>('readPlayListAsync', ''))).toPromise(); 
    }

    async readArtistsAsync():Promise<string>{
        return this.http.post<string>(data.read_database_async.url_read_database + data.read_database_async.controller_artist+
            data.read_database_async.path_artist.read_all_artists, undefined, this.getHttpOptionsWithoutParam())
            .pipe(catchError(this.handleError<string>('readArtistsAsync', ''))).toPromise();  
    } 

    private getHttpOptionsWithoutParam =()=> {
        return {
        headers: new HttpHeaders({
          'Content-Type':  'application/json'
        })}; 
      };
}