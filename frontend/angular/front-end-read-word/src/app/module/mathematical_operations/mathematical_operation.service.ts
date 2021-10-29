import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import data from "../../config/url_config.json";
import { catchError } from "rxjs/operators";
import { HandleError, HttpErrorHandler } from "src/app/config/http-error-handler.service";


@Injectable()
export class MathematicalOperationService{
    private handleError: HandleError;

    constructor (private http: HttpClient,
        httpErrorHandler: HttpErrorHandler) {
        this.handleError = httpErrorHandler.createHandleError('MathematicalOperationService');
    } 
   
    public async calculusFactorial(factorial:number):Promise<void>{
        return this.http.post<void>(data.mathematical_operation.url_mathematical_operation + data.mathematical_operation.controller_mathematical+
            data.mathematical_operation.path_mathematical_operation.calculus_factorial,undefined,this.getHttpOptionsWithPArams(factorial)).
            pipe(catchError(this.handleError<void>('calculusFactorial'))).toPromise(); 
    }

    public async calculusFibonacci(fibonacci:number):Promise<void>{
        return this.http.post<void>(data.mathematical_operation.url_mathematical_operation + data.mathematical_operation.controller_mathematical+
            data.mathematical_operation.path_mathematical_operation.calculus_fibonacci,undefined,this.getHttpOptionsWithPArams(fibonacci)).
            pipe(catchError(this.handleError<void>('calculusFibonacci'))).toPromise(); 
    }

    private getHttpOptionsWithoutParam =()=> {
        return {
        headers: new HttpHeaders({
            'Content-Type':  'application/json'
        })}; 
    };
    private getHttpOptionsWithPArams =(id:number)=> {
        return {
        headers: new HttpHeaders({
          'Content-Type':  'application/json'
        }), 
        params: new HttpParams().set('number', id)
        }; 
      };
}