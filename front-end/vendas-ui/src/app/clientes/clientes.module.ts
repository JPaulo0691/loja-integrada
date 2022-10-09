import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { DialogCadClientesComponent } from './dialog-cad-clientes/dialog-cad-clientes.component';
import { ReactiveFormsModule } from '@angular/forms';


import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesComponent } from './clientes/clientes.component';


@NgModule({
  declarations: [
    ClientesComponent,
    DialogCadClientesComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    AppMaterialModule,
    ReactiveFormsModule
  ]
})
export class ClientesModule { }
