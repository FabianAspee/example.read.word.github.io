import { Component, Input, Output, EventEmitter } from "@angular/core";

@Component({
    selector:'custom-button',
    templateUrl:'./button.template.html',
    styleUrls: ['./button.style.css']
})

export class ButtonComponent { 
    @Input() name:string = ''; 
    @Output() click_event = new EventEmitter();
    call_parent = () => this.click_event.emit();
}