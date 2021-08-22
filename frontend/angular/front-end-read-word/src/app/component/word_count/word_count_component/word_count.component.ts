import { Component } from '@angular/core'; 
import { Receiver } from '../../../module/stompjs/Receiver.module'; 
import { WordCountService } from '../word_count.service';

@Component({
  selector: 'word-count',
  templateUrl: './word_count.template.html', 
  providers: [WordCountService, Receiver],
  styleUrls: ['./word_count.style.css']
})
export class WordCountComponent{ 
  title = 'Count Word Example';  

  constructor(private configService: WordCountService,
    private receiver: Receiver) {
    receiver.connect(this.printValueRabbit)
  } 
   
   
  rabbit_call_word_count_example_spring = "Real Time Name Word Count Spring-RabbitMQ";
  rabbit_call_word_count_example_net_core_api = "Real Time Name Word Count NetCore-RabbitMQ";
  rabbit_call_word_count_example_akka_rest = "Real Time Name Word Count AkkaRest-RabbitMQ";
  word_count_example_grpc = "Real Time Word Count gRPC";
 
  place_holder_result = "Word count result";
  place_holder_total_word_view = "Words to be seen";
  place_holder_length_word = "Length word";
  
  countWordSpringRabbitMQ= ()=> this.initCountWordSpringRabbitMQ();
  countWordNetCoreRabbitMQ= ()=> this.initCountWordNetCoreRabbitMQ();
  countWordAkkaRestRabbitMQ= ()=> this.initCountWordAkkaRestRabbitMQ();
  countWordgRPC= ()=> this.initCountWordgRPC();
 
  
  private initCountWordgRPC(): void { 
    this.configService.initCountWordgRPC()
    .subscribe(allNameTurbine => console.log(allNameTurbine));
  }

  private initCountWordSpringRabbitMQ(): void { 
    this.configService.initCountWordSpringRabbitMQ()
    .subscribe(allNameTurbine => console.log(allNameTurbine));
  }

  private initCountWordNetCoreRabbitMQ() {
    this.configService.initCountWordNetCoreRabbitMQ()
    .subscribe(allNameTurbine => console.log(allNameTurbine));
  }
  private initCountWordAkkaRestRabbitMQ() {
    this.configService.initCountWordAkkaRestRabbitMQ()
    .subscribe(allNameTurbine => console.log(allNameTurbine));
  }
  private printValueRabbit=(x:string):string=>{
    this.place_holder_result="";
    this.place_holder_result = x;
    return this.place_holder_result
  }
 


}