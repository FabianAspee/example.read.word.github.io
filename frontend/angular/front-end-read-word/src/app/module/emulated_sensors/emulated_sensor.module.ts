import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms"; 
import { EmulatedSensorComponent } from "./emulated_sensor_component/emulated_sensor.component";
import { EmulatedSensorRoutingModule } from "./emulated_sensor_routing.module";

@NgModule({
    imports:[
        CommonModule,
        FormsModule,
        EmulatedSensorRoutingModule
    ],
    declarations:[ 
        EmulatedSensorComponent
    ]
})

export class EmulatedSensorModule{}