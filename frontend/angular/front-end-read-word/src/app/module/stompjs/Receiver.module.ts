import { Injectable, OnInit } from "@angular/core";
import { Stomp } from "@stomp/stompjs";

let print : (value:string)=>string;
@Injectable()
export class Receiver{
    ws = new WebSocket('ws://localhost:15674/ws');
    client = Stomp.over(this.ws);  
    
    connect=(print_function:(value:string)=>string)=> { 
        print = print_function; 
        this.client.connect('guest', 'guest', this.on_connect, this.on_error, '/'); 
    } 

    on_connect = () => this.client.subscribe("/queue/demo-rabbitmq", (receive_value) =>
            print(receive_value.body));
    
    on_error =  (x:any) => console.log('error',x);
    

    

} 