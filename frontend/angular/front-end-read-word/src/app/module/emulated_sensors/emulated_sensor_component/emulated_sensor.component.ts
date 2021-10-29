import { Component } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { EmulatedSensorService } from "../emulated_sensor.service";


@Component({
    selector: 'emulated-sensor-component',
    templateUrl: './emulated_sensor.template.html',
    providers: [EmulatedSensorService],
    styleUrls: ['./emulated_sensor.style.css']
})
export class EmulatedSensorComponent{
    constructor(private service:EmulatedSensorService, route: ActivatedRoute){}
}