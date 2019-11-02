import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainpageComponent } from './components/mainpage/mainpage.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserpageComponent } from './components/userpage/userpage.component';

const routes: Routes = [
  { path: 'main', component: MainpageComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'user/:nickname', component: UserpageComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
