import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCadClientesComponent } from './dialog-cad-clientes.component';

describe('DialogCadClientesComponent', () => {
  let component: DialogCadClientesComponent;
  let fixture: ComponentFixture<DialogCadClientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogCadClientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogCadClientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
