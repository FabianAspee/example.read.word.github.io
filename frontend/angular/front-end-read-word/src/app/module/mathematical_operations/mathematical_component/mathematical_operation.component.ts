import { Component } from "@angular/core";
import { ReceiverVertx } from "../../vertx_config/receiver_module_vertx";
import { MathematicalOperationService } from "../mathematical_operation.service";  
import { MessageService, PrimeNGConfig } from "primeng/api";

@Component({
    selector: 'mathematical_operation',
    templateUrl: './mathematical_operation.template.html', 
    providers: [MathematicalOperationService, ReceiverVertx, MessageService],
    styleUrls: ['./mathematical_operation.style.css']
  })
export class MathematicalOperationComponent{ 
    constructor(private receiverVertx: ReceiverVertx, 
        private messageService: MessageService,
        private service:MathematicalOperationService,
        private primengConfig: PrimeNGConfig){  
            receiverVertx.onopen(this.setStatus, this.messageService)
            primengConfig.ripple = true;
        }
        place_holder_insert_number = "Insert number to calculus"
        place_holder_result = "Final result"
        calculus_factorial = "Factorial Calculus"
        calculus_fibonacci = "Fibonacci Calculus" 
   
    public async calculusFactorial(){
        await this.service.calculusFactorial(30).then(()=>console.log("call factorial ok"))
    }

    public async calculusFibonacci(){
        await this.service.calculusFibonacci(40).then(()=>console.log("call fibonacci ok"))
    }
    private setStatus(value:string, messageService:MessageService){
        console.log(messageService)
        messageService.add({severity:'success', summary: 'Success', detail: value});
    }
    public onConfirm() {
        this.messageService.clear('c');
    }

    public onReject() {
        this.messageService.clear('c');
    }
    
    public clear() {
        this.messageService.clear();
    }
}