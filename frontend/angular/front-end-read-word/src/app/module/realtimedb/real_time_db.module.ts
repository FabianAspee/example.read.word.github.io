import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";

import { RealTimeDatabaseComponent } from "./realtimedb_component/real_time_db.component";

import { RealTimeDatabaseRoutingModule } from "./real_time_db_routing.module";
import { SharedModule } from "src/app/module/shared_module";

@NgModule({
    imports: [
      CommonModule,
      FormsModule,
      RealTimeDatabaseRoutingModule, 
      SharedModule
    ],
    declarations: [ 
      RealTimeDatabaseComponent
    ]
  })
  
  export class RealTimeDatabaseModule {} 