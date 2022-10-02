import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Clientes } from 'src/app/model/clientes';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  clientes$: Observable<Clientes[]>; //$ significa que tá trabalhando com Observables
  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email'];

  constructor(private clientesService : ClientesService ) {

    this.clientes$ = this.clientesService.list();
  }

  ngOnInit(): void {
  }

}
