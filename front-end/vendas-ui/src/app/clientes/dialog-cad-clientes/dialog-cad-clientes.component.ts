import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClientesService } from 'src/app/services/clientes.service';


@Component({
  selector: 'app-dialog-cad-clientes',
  templateUrl: './dialog-cad-clientes.component.html',
  styleUrls: ['./dialog-cad-clientes.component.scss']
})
export class DialogCadClientesComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private service: ClientesService,
    private snackBar: MatSnackBar
    ) {
    this.form = this.formBuilder.group({
      nome: [null],
      cpf: [null],
      email: [null]
    });
  }

  ngOnInit(): void {
  }

  onCancel(){
    console.log("cancel");

  }

  onSave(){
    this.service.save(this.form.value).subscribe(cadastrar =>console.log('success'),error =>{
      this.snackBar.open('Erro de Cadastramento', '', {duration: 3000});
    });
  }

}
