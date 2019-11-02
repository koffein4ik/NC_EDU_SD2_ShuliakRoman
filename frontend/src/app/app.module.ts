import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { IgxDatePickerModule} from 'igniteui-angular';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { AppRoutingModule } from './app-routing.module';
import { RegistrationComponent } from './components/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserpageComponent } from './components/userpage/userpage.component';

@NgModule({
  declarations: [
    AppComponent,
    MainpageComponent,
    RegistrationComponent,
    UserpageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    IgxDatePickerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
