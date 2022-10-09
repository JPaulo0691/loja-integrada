import { Injectable } from '@angular/core';
import { Clientes } from '../model/clientes';
import {HttpClient} from '@angular/common/http';
import { delay, first, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  private readonly api = '/api/vendas';

  constructor(private HttpClient: HttpClient) { }

  list() {
    return this.HttpClient.get<Clientes[]>(this.api).
    pipe(
      first(),
      delay(1000),
      tap(clientes => console.log(clientes))
    );
  }

  save(record: Clientes){
    return this.HttpClient.post<Clientes>(this.api, record).pipe(first());
  }
}
