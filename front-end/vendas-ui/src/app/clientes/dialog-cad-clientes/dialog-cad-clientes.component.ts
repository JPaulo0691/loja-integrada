import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Location} from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClientesService } from 'src/app/services/clientes.service';
import { ClientesDTO } from '../../model/clientesDTO';


@Component({
  selector: 'app-dialog-cad-clientes',
  templateUrl: './dialog-cad-clientes.component.html',
  styleUrls: ['./dialog-cad-clientes.component.scss']
})
export class DialogCadClientesComponent implements OnInit {

  form = this.formBuilder.group({
    nome: [''],
    cpf: [''],
    email: ['']
  });

  msg?: string;
  erro?: string;

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: ClientesService,
    private snackBar: MatSnackBar,
    private location: Location
    ) {
    //this.form =
  }

  ngOnInit(): void {
  }

  onSave(){

    this.service.save(this.form.value).subscribe(
      response =>{
        this.onSuccess();
      }, exception =>{
        this.onError();
      }
    );
  };

  onCancel(){
    this.location.back();

  }

  onSuccess(){
    this.snackBar.open('Cadastro Salvo com Sucesso!', '', {duration: 3000});
    this.onCancel();
  }

  onError(){
    this.snackBar.open('Erro de Cadastramento', '', {duration: 3000});
  }

}
