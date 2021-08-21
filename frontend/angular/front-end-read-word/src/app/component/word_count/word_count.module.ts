import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { WordCountComponent } from './word_count_component/word_count.component'; 

import { WordCountRoutingModule } from './word_count_routing.module';
import { TextAreaComponent } from '../textarea/textarea.component';
import { InputComponent } from '../input/input.component';
import { ButtonComponent } from '../button/button.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    WordCountRoutingModule
  ],
  declarations: [ 
    TextAreaComponent,
    InputComponent,
    ButtonComponent,
    WordCountComponent
  ]
})

export class WordCountModule {} 