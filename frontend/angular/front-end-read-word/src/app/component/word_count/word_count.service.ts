import { HttpClient} from "@angular/common/http"; 
import data from "../../config/url_config.json";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { catchError } from "rxjs/operators";
import { HandleError, HttpErrorHandler } from "src/app/config/http-error-handler.service";

@Injectable()
export class WordCountService{ 
    private handleError: HandleError;

    constructor (private http: HttpClient,
        httpErrorHandler: HttpErrorHandler) {
        this.handleError = httpErrorHandler.createHandleError('WordCountService');
    }

    initCountWordgRPC= ():Observable<string>=>this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('infoCountWordgRPC', ''))); 

    initCountWordSpringRabbitMQ=():Observable<string>=> this.http.get<string>(data.word_count_info_spring.url_word_count + 
        data.word_count_info_spring.path.path_spring_rabbitmq).pipe(catchError(this.handleError<string>('initCountWordSpringRabbitMQ', ''))); 
    
    initCountWordNetCoreRabbitMQ=():Observable<string>=> this.http.get<string>(data.word_count_info_net_core.url_word_count + 
        data.word_count_info_net_core.path.path_netcore_rabbitmq).pipe(catchError(this.handleError<string>('initCountWordNetCoreRabbitMQ', ''))); 
    
    initCountWordAkkaRestRabbitMQ=():Observable<string>=> this.http.get<string>(data.word_count_info_akka_rest.url_word_count + 
        data.word_count_info_akka_rest.path.path_akkarest_rabbitmq).pipe(catchError(this.handleError<string>('initCountWordAkkaRestRabbitMQ', ''))); 

}