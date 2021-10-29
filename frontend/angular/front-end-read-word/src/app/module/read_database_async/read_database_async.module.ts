import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
  
import { SharedModule } from "src/app/module/shared_module";
import { ReadDatabaseAsyncComponent } from "./read_database_async_component/read_database_async.component";
import { ReadDatabaseAsyncRoutingModule } from "./read_database_async_routing.module";

@NgModule({
    imports: [
      CommonModule,
      FormsModule,
      ReadDatabaseAsyncRoutingModule, 
      SharedModule
    ],
    declarations: [ 
      ReadDatabaseAsyncComponent
    ]
  })
  
  export class ReadDatabaseAsyncModule {} 