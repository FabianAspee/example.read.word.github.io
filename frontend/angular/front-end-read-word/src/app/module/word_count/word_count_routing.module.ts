import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WordCountComponent } from './word_count_component/word_count.component';

const wordCountRoutes: Routes = [  
  { path: 'wordcount', redirectTo: '/wordcounturl' }, 
  { path: 'wordcounturl', component: WordCountComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(wordCountRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class WordCountRoutingModule { }