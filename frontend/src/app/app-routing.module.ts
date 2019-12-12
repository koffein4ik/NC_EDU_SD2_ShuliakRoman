import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistrationpageComponent } from './pages/registrationpage/registrationpage.component';
import { UserpageComponent } from './pages/userpage/userpage.component';
import { FeedpageComponent } from './pages/feedpage/feedpage.component'; 
import { AddpostpageComponent } from './pages/addpostpage/addpostpage.component';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { HashtagpageComponent } from './pages/hashtagpage//hashtagpage.component';
import { MyfeedComponent } from './pages/myfeed/myfeed.component';
import { ReportspageComponent } from './pages/reportspage/reportspage.component';
import { EdituserinfopageComponent } from './pages/edituserinfopage/edituserinfopage.component';
import { AlluserspageComponent } from './pages/alluserspage/alluserspage.component';

const routes: Routes = [
  { path: 'main', component: FeedpageComponent },
  { path: 'registration', component: RegistrationpageComponent },
  { path: 'user/:nickname', component: UserpageComponent },
  { path: 'feed', component: FeedpageComponent }, 
  { path: 'addpost', component: AddpostpageComponent },
  { path: 'login', component: LoginpageComponent },
  { path: 'hashtag/:hashtag', component: HashtagpageComponent },
  { path: 'hashtag', component: HashtagpageComponent },
  { path: 'myfeed', component: MyfeedComponent },
  { path: 'reports', component: ReportspageComponent },
  { path: 'edituserinfo', component: EdituserinfopageComponent },
  { path: 'allusers', component: AlluserspageComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
