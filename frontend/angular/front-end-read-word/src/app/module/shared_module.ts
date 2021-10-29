import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { ButtonComponent } from '../component/button/button.component';
import { InputComponent } from '../component/input/input.component';
import { TextAreaComponent } from '../component/textarea/textarea.component';

@NgModule({
 imports:      [ CommonModule ],
 declarations: [ InputComponent, TextAreaComponent, ButtonComponent ],
 exports:      [ InputComponent, TextAreaComponent, ButtonComponent,
                 CommonModule, FormsModule ]
})
export class SharedModule { }