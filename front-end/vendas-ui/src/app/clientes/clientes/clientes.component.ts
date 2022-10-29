import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Clientes } from 'src/app/model/clientes';
import { ClientesService } from 'src/app/services/clientes.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.scss']
})
export class ClientesComponent implements OnInit {

  clientes$: Observable<Clientes[]>; //$ significa que tá trabalhando com Observables
  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'actions'];

  constructor(
    private clientesService : ClientesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.clientes$ = this.clientesService.list();
  }

  ngOnInit(): void {
  }

  onCadastrar(){
    this.router.navigate(['cadastrar'], {relativeTo: this.route});
  }
  sortAsc(){
    this.clientes$ = this.clientesService.sortList();
  }

}
