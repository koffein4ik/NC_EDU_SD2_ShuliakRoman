import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { IgxCarouselModule } from 'igniteui-angular';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
 
import { AppComponent } from './app.component';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { AppRoutingModule } from './app-routing.module';
import { RegistrationComponent } from './pages/registrationpage/components/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserpageComponent } from './pages/userpage/userpage.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { RegistrationpageComponent } from './pages/registrationpage/registrationpage.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FeedpageComponent } from './pages/feedpage/feedpage.component';
import { PostComponent } from './pages/feedpage/components/post/post.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { AddpostpageComponent } from './pages/addpostpage/addpostpage.component';
import { LikedislikeComponent } from './pages/feedpage/components/likedislike/likedislike.component';
import { CommentComponent } from './pages/feedpage/components/comment/comment.component';
import { HashtagpageComponent } from './pages/hashtagpage/hashtagpage.component';
import { MyfeedComponent } from './pages/myfeed/myfeed.component';
import { ReportdialogComponent } from './pages/components/reportdialog/reportdialog.component';
import { ReportspageComponent } from './pages/reportspage/reportspage.component';
import { EdituserinfopageComponent } from './pages/edituserinfopage/edituserinfopage.component';
import { AlluserspageComponent } from './pages/alluserspage/alluserspage.component';
import { APIInterceptor } from "./interceptors/api-interceptor";
import { UserService } from "./services/user.service";

@NgModule({
  declarations: [
    AppComponent,
    MainpageComponent,
    RegistrationComponent,
    UserpageComponent,
    HomepageComponent,
    RegistrationpageComponent,
    FooterComponent,
    NavbarComponent,
    FeedpageComponent,
    PostComponent,
    LoginpageComponent,
    AddpostpageComponent,
    LikedislikeComponent,
    CommentComponent,
    HashtagpageComponent,
    MyfeedComponent,
    ReportdialogComponent,
    ReportspageComponent,
    EdituserinfopageComponent,
    AlluserspageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    IgxCarouselModule,
    MatCarouselModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatTableModule
  ],
  entryComponents: [
    ReportdialogComponent
  ],
  providers: [UserService, APIInterceptor, {
    provide: HTTP_INTERCEPTORS,
    useClass: APIInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
