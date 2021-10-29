import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';  
import { ReadDatabaseAsyncComponent } from './read_database_async_component/read_database_async.component';

const readDatabaseRoutes: Routes = [  
  { path: 'readdatabaseasync', redirectTo: '/readdatabaseasyncurl' }, 
  { path: 'readdatabaseasyncurl', component: ReadDatabaseAsyncComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(readDatabaseRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ReadDatabaseAsyncRoutingModule { }