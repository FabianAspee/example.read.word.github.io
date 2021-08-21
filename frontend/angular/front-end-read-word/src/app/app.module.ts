import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmulatedSensorModule } from './component/emulated_sensors/emulated_sensor.module';
import { NavigationComponent } from './component/navigation/navigation.component';
import { PageNotFoundComponent } from './component/page_not_found/page_not_found.component';
import { WordCountModule } from './component/word_count/word_count.module';
import { HttpErrorHandler } from './config/http-error-handler.service';
import { MessageService } from './config/message.service';

@NgModule({
  declarations: [
    AppComponent, 
    NavigationComponent,
    PageNotFoundComponent, 
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, 
    WordCountModule,
    EmulatedSensorModule, 
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [HttpErrorHandler,MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
