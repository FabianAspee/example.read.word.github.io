import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { EmulatedSensorComponent } from "./emulated_sensor_component/emulated_sensor.component";

const emulatedSensorRoute:Routes=[
    {path:'emulatedsensor' , redirectTo:'/emulatedsensorsystem'},
    {path:'emulatedsensorsystem', redirectTo:'/emulatedsensorsystem'},
    {path:'emulatedsensorsystem', component: EmulatedSensorComponent}
]
@NgModule({
  imports: [
    RouterModule.forChild(emulatedSensorRoute)
  ],
  exports: [
    RouterModule
  ]
})
export class EmulatedSensorRoutingModule{}