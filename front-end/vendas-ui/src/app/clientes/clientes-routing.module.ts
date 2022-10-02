import { ClientesModule } from './clientes.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientesComponent } from './clientes/clientes.component';

//primeiro config o arquivo de rotas clientes, dps vai para o global
const routes: Routes = [
  {path: '', component: ClientesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
