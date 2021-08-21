import { Component, Input } from "@angular/core";

@Component({
    selector:'custom-text-area',
    templateUrl:'./textarea.template.html',
    styleUrls: ['./textarea.style.css']
})

export class TextAreaComponent { 
    @Input() name = '';
  }