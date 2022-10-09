import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientesComponent } from './clientes/clientes.component';
import { DialogCadClientesComponent } from './dialog-cad-clientes/dialog-cad-clientes.component';

//primeiro config o arquivo de rotas clientes, dps vai para o global
const routes: Routes = [
  {path: '', component: ClientesComponent},
  {path: 'cadastrar', component: DialogCadClientesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
