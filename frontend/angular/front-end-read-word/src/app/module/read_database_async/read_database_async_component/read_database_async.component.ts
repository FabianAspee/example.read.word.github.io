import { Component } from "@angular/core";
import { Receiver } from "src/app/module/stompjs/Receiver.module"; 
import { ReadDatabaseAsyncService } from "../read_database_async.service";

@Component({
    selector: 'read-database-async',
    templateUrl: './read_database_async.template.html', 
    providers: [ReadDatabaseAsyncService, Receiver],
    styleUrls: ['./read_database_async.style.css']
  })
  export class ReadDatabaseAsyncComponent{ 
    title = 'Read Database Async Example';  
  
    constructor(private configService: ReadDatabaseAsyncService) {
    } 
    read_artist_database_async = "Read Artists Async"
    read_playlist_database_async = "Read PlayLists Async" 

    place_holder_result = "Read Database result";
    
    public async readArtistAsync():Promise<void>{
      await this.configService.readArtistsAsync().then(x=>
        this.place_holder_result = x
      )
    }
    public async readPlayListAsync():Promise<void>{
      await this.configService.readPlayListAsync().then(x=>
        this.place_holder_result = x
      )
    }
}