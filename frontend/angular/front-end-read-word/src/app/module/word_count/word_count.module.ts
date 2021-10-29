import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { WordCountComponent } from './word_count_component/word_count.component'; 

import { WordCountRoutingModule } from './word_count_routing.module';
import { SharedModule } from 'src/app/module/shared_module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    WordCountRoutingModule, 
    SharedModule
  ],
  declarations: [ 
    WordCountComponent
  ]
})

export class WordCountModule {} 