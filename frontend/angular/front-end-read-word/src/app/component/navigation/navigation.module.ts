import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
 
import {RouterModule} from '@angular/router';
import { NavigationComponent } from './navigation.component';

@NgModule({
  imports: [BrowserModule, NgbModule, RouterModule.forRoot([])],
  declarations: [NavigationComponent],
  exports: [NavigationComponent],
  bootstrap: [NavigationComponent]
})
export class NgbdCollapseNavbarModule {
}