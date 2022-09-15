import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Jornada } from '../modelo/Jornada';
import { JornadaService } from './../servicio/jornada.service';

@Component({
  selector: 'app-jornada',
  templateUrl: './jornada.component.html',
  styleUrls: ['./jornada.component.scss']
})
export class JornadaComponent implements OnInit {

  public formJornada!: FormGroup;

  public listJornadas: Array<Jornada> = [];

  public jornada: Jornada = new Jornada();

  public guardar: boolean = true;

  public unaJornada: boolean = false;

  public jornadaSola: number = -1;


  
  constructor(private formBuilder: FormBuilder, private jornadaService: JornadaService) { }

  ngOnInit(): void {
    this.initForm();
    this.obtenerJornadas();
  }

  initForm(){
    this.formJornada = this.formBuilder.group({
      id: new FormControl ('', [Validators.required]),
      tipo: new FormControl ('', [Validators.required]),
      fecha: new FormControl ('', [Validators.required]),
      horarioEntrada: new FormControl ('', [Validators.required]),
      horarioSalida: new FormControl ('', [Validators.required]),
      buscar: new FormControl ('', [Validators.required])
    });
  }

  limpiarFormulario(): void{
    this.formJornada.reset();
  }

  abrirGuardarJornada(): void{
    this.limpiarFormulario();
    this.guardar = true;
  }

  abrirModificarJornada(): void{
    this.limpiarFormulario();
    this.guardar = false;
  }

  abrirModificarJornadaConJornada(jornada: Jornada): void{
    this.limpiarFormulario();
    this.guardar = false;
    this.formJornada.get('id')?.setValue(jornada.id);
    this.formJornada.get('tipo')?.setValue(jornada.tipo);
    this.formJornada.get('fecha')?.setValue(jornada.fecha);
    this.formJornada.get('horarioEntrada')?.setValue(jornada.horarioEntrada);
    this.formJornada.get('horarioSalida')?.setValue(jornada.horarioSalida);
    window.scroll(0,0);
  }

  seleccionarCualModificarJornada(): void{
    for(let item of this.listJornadas){
      if(item.id === this.formJornada.get('id')?.value){
        this.jornada = item;
        break;
      }
    }
    
    if(this.jornada.empleadoId === null) {
      this.modificarJornada();
    } else {
      this.modificarJornadaDeUnEmpleado(this.jornada.empleadoId!);
    }

    this.jornada.id = undefined;
    this.jornada.tipo = undefined;
    this.jornada.fecha = undefined;
    this.jornada.horarioEntrada = undefined;
    this.jornada.horarioSalida = undefined;
    this.jornada.empleadoId = undefined;
  }

  mostrarJornada():void{
    this.unaJornada = true;
    this.jornadaSola = this.formJornada.get('buscar')?.value;
  }

  mostrarJornadas():void{
    this.unaJornada = false;
  }

  crearJornada(): void{
    var jornada = new Jornada();
    jornada.tipo = this.formJornada.get('tipo')?.value;
    jornada.fecha = this.formJornada.get('fecha')?.value;
    jornada.horarioEntrada = this.formJornada.get('horarioEntrada')?.value;
    jornada.horarioSalida = this.formJornada.get('horarioSalida')?.value;

    this.jornadaService.crearJornada(jornada).subscribe(res => {
      this.formJornada.reset();
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el crear jornada.'));
  }

  modificarJornada(): void{
    var jornada = new Jornada();
    jornada.tipo = this.formJornada.get('tipo')?.value;
    jornada.fecha = this.formJornada.get('fecha')?.value;
    jornada.horarioEntrada = this.formJornada.get('horarioEntrada')?.value;
    jornada.horarioSalida = this.formJornada.get('horarioSalida')?.value;

    this.jornadaService.modificarJornada(this.formJornada.get('id')?.value, jornada).subscribe(res => {
      this.formJornada.reset();
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el modificar jornada.'));
  }

  eliminarJornada(jornadaId: any): void{
    this.jornadaService.eliminarJornada(jornadaId).subscribe(res => {
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el eliminar jornada.'));
  }

  obtenerJornada(): void{
    this.jornadaService.obtenerJornada(this.formJornada.get('buscar')?.value).subscribe(res => {
      this.jornada = res;
      console.log('Se obtuvo la jornada: ' + res);
    },
    error => console.error('Falló el obtener jornada.'));
  }

  obtenerJornadas(): void{
    this.jornadaService.obtenerJornadas().subscribe(res => {
      this.listJornadas = res;
      console.log('La funcion de obtener todas las jornadas funciona correctamente.');
    },
    error => console.error('Falló el obtener jornadas.'));
  }

  modificarJornadaDeUnEmpleado(empleadoId : number): void{
    var jornada = new Jornada();
    jornada.tipo = this.formJornada.get('tipo')?.value;
    jornada.fecha = this.formJornada.get('fecha')?.value;
    jornada.horarioEntrada = this.formJornada.get('horarioEntrada')?.value;
    jornada.horarioSalida = this.formJornada.get('horarioSalida')?.value;

    this.jornadaService.modificarJornadaDeUnEmpleado(empleadoId, this.formJornada.get('id')?.value, jornada).subscribe(res => {
      this.formJornada.reset();
      console.log('Se modificó la jornada del empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el modificar una jornada de un empleado.'));
  }

  eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): void{
    this.jornadaService.eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId, jornadaId).subscribe(res => {
      console.log('Se eliminó la jornada del empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el eliminar una jornada que está dentro de un empleado.'));
  }

}


