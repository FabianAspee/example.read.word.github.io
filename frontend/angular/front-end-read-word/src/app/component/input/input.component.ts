import { Component, Input, Output } from "@angular/core"; 

@Component({
    selector:'custom-input',
    templateUrl:'./input.template.html',
    styleUrls: ['./input.style.css']
})

export class InputComponent { 
    @Input() name = ''; 
    value = '';
    getValue = ()=>this.value;
  }