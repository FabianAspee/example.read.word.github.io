import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { RealTimeDatabaseComponent } from './realtimedb_component/real_time_db.component';

const realTimeDatabaseRoutes: Routes = [  
  { path: 'realtimedatabase', redirectTo: '/realtimedatabaseurl' }, 
  { path: 'realtimedatabaseurl', component: RealTimeDatabaseComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(realTimeDatabaseRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class RealTimeDatabaseRoutingModule { }