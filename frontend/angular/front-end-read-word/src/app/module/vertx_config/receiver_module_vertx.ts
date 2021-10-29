import { Injectable } from "@angular/core";
import EventBus from "@vertx/eventbus-bridge-client.js";   
import { MessageService } from "primeng/api";
import data from "../../config/url_config.json";
@Injectable()
export class ReceiverVertx{
    eventBus = new EventBus('http://localhost:8888/eventbus/'); 
    
    onopen = (print_function:(input:string,messageService:MessageService)=>void, messageService:MessageService) => 
      this.eventBus.onopen = ()=>{ 
        // set a handler to receive a message
        this.eventBus.registerHandler('some-address', (error:string, message:string) =>{
          print_function(JSON.parse(JSON.stringify(message)).body,messageService) 
          console.log('received a message: ' + JSON.stringify(message));
       });
       this.eventBus.enableReconnect(true);
      } 
      
    onopen_database = (print_function:(input:string)=>void) => 
    this.eventBus.onopen = ()=>{ 
      // set a handler to receive a message
      this.eventBus.registerHandler('some-address', (error:string, message:string) =>{
        print_function(JSON.parse(JSON.stringify(message)).body) 
        console.log('received a message: ' + JSON.stringify(message));
      });
      this.eventBus.enableReconnect(true);
    } 
    onclose=()=>this.eventBus.close();
}  
 