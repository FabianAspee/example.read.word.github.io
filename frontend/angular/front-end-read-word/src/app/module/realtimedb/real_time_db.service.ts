import { HttpClient, HttpHeaders} from "@angular/common/http"; 
import data from "../../config/url_config.json";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { HandleError, HttpErrorHandler } from "src/app/config/http-error-handler.service";

@Injectable()
export class RealTimeDatabaseService{ 
    private handleError: HandleError;

    constructor (private http: HttpClient,
        httpErrorHandler: HttpErrorHandler) {
        this.handleError = httpErrorHandler.createHandleError('RealTimeDatabaseService');
    }

    async readAllPlayListVertx():Promise<string>{
        return this.http.post<string>(data.read_database_vertx.url_read_database + data.read_database_vertx.controller_playlist+
            data.read_database_vertx.path_playlist.read_all_playlist,undefined,this.getHttpOptionsWithoutParam()).
            pipe(catchError(this.handleError<string>('readAllPlayListVertx', ''))).toPromise(); 
    
    }

    async readAllArtistVertx():Promise<string>{
        return this.http.post<string>(data.read_database_vertx.url_read_database + data.read_database_vertx.controller_artist+
            data.read_database_vertx.path_artist.read_all_artists, undefined,this.getHttpOptionsWithoutParam())
            .pipe(catchError(this.handleError<string>('readAllNameVertx', ''))).toPromise();
    } 

    readAllPlayListKafka= ():Observable<string>=>this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('readAllPlayListKafka', ''))); 

    readAllNameKafka= ():Observable<string>=>this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('readAllNameKafka', ''))); 

    readAllPlayListRbbitMQ= ():Observable<string>=>this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('readAllPlayListRbbitMQ', ''))); 

    readAllNameRabbitMQ= ():Observable<string>=>this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('readAllNameRabbitMQ', ''))); 

    private getHttpOptionsWithoutParam =()=> {
        return {
        headers: new HttpHeaders({
            'Content-Type':  'application/json'
        })}; 
        };

}