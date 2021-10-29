import { Injectable } from "@angular/core";
import EventBus from "@vertx/eventbus-bridge-client.js";
let print : (value:string)=>string;

@Injectable()
export class ReceiverVertx{
    eventBus = new EventBus('http://127.0.0.1:8888/eventbus'); 

    onopen = (print_function:(input:string)=>string) => { 
      print = print_function
      console.log("hola")
     // set a handler to receive a message
     this.eventBus.registerHandler('some-address',function (error:string, message:string)  {
       console.log('received a message: ' + JSON.stringify(message));
     });
    
     // send a message
     this.eventBus.send('some-address', {name: 'tim', age: 587});
     this.eventBus.enableReconnect(true);
    }
    onclose=()=>this.eventBus.close();
} 