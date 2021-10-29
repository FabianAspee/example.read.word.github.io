import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './component/page_not_found/page_not_found.component';

const routes: Routes = [
  {
    path: 'emulatedsensorsystem',
    loadChildren: () => import('./module/emulated_sensors/emulated_sensor.module')
    .then(module=> module.EmulatedSensorModule),
    data: { preload: true }
  }, 
  { 
    path: 'wordcounturl', loadChildren: () => import('./module/word_count/word_count.module')
    .then(module => module.WordCountModule),
    data: { preload: true }
  },
  { 
    path: 'realtimedatabaseurl', loadChildren: () => import('./module/realtimedb/real_time_db.module')
    .then(module => module.RealTimeDatabaseModule),
    data: { preload: true }
  }, 
  { path: '',   redirectTo: '/wordcounturl', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent },
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
  
