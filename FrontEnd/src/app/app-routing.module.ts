import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceComponent } from './service/service.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { SignupComponent } from './signup/signup.component';
import { SurpriseComponent } from './surprise/surprise.component';
import { RedbuttonComponent } from './redbutton/redbutton.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { ThisBookComponent } from './this-book/this-book.component';
import { flush } from '@angular/core/testing';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path:'service', component:ServiceComponent, pathMatch:'full'},
  { path:'contact', component:ContactComponent, pathMatch:'full' },
  { path:'about', component:AboutComponent, pathMatch:'full' },
  { path:'signup', component:SignupComponent, pathMatch:'full' },
  { path:'login', component:LoginComponent, pathMatch:'full' },
  { path:'surprise', component:SurpriseComponent, pathMatch:'full' },
  { path:'redbutton', component:RedbuttonComponent, pathMatch:'full' },
  { path:'users', component:UserComponent, pathMatch:'full' },
  { path:'thisbook', component:ThisBookComponent, pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
