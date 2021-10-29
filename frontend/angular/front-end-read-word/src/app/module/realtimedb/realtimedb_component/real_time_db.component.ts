import { Component, OnDestroy } from "@angular/core"; 
import { ReceiverVertx } from "../../vertx_config/receiver_module_vertx";
import { RealTimeDatabaseService } from "../real_time_db.service";

@Component({
    selector: 'real-time-database',
    templateUrl: './real_time_db.template.html', 
    providers: [RealTimeDatabaseService, ReceiverVertx],
    styleUrls: ['./real_time_db.style.css']
  })
  export class RealTimeDatabaseComponent implements OnDestroy{ 
    title = 'Real Time Database Example';  
  
    constructor(private configService: RealTimeDatabaseService,
      private receiver: ReceiverVertx) {
        receiver.onopen_database(this.printValueVertx)
    } 
    ngOnDestroy(): void {
      this.receiver.onclose();
    }
    real_time_read_artists_database_vertx = "Read Artists With Vertx"
    real_time_read_playlist_database_vertx = "Read PlayList With Vertx"
    real_time_read_artists_database_kafka = "Read Artists With Kafka"
    real_time_read_playlist_database_kafka = "Read PlayList With Kafka"
    real_time_read_artists_database_rabbitmq = "Read Artists With RabbitMQ"
    real_time_read_playlist_database_rabbitmq = "Read PlayList With RabbitMQ"

    place_holder_result = "Read Database result";
    private cleanPlaceHolder=():string=>""
    public async readArtistsVertx(){
      this.place_holder_result = this.cleanPlaceHolder();
       await this.configService.readAllArtistVertx().then(x=>console.log("make call ok"));
    }
    public async readPlayListVertx(){
      this.place_holder_result = this.cleanPlaceHolder();
      await this.configService.readAllPlayListVertx().then(x=>console.log("make call ok"));
   }
    public readArtistsKafka=():void=>{}
    public readPlayListKafka=():void=>{}
    public readArtistsRabbitMQ=():void=>{}
    public readPlayListRabbitMQ=():void=>{}

    private printValueVertx=(x:string):void=>{ 
      this.place_holder_result+=x+"\n";
    }

  }