import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './modulo/home/home.component';
import { EmpleadoComponent } from './modulo/empleado/empleado.component';
import { JornadaComponent } from './modulo/jornada/jornada.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'empleado',
    component: EmpleadoComponent
  },
  {
    path: 'jornada',
    component: JornadaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],


exports: [RouterModule]
})
export class AppRoutingModule { }
