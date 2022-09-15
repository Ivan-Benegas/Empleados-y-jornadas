import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Jornada } from '../modelo/Jornada';
import { Empleado } from '../modelo/Empleado';
import { EmpleadoService } from './../servicio/empleado.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.scss']
})
export class EmpleadoComponent implements OnInit {

  public formEmpleado!: FormGroup;

  public formJornada!: FormGroup;

  public listEmpleados: Array<Empleado> = [];

  public empleado: Empleado = new Empleado();

  public guardarEmpleados: boolean = true;

  public modificarEmpleados: boolean = false;

  public asignarJornadas: boolean = false;

  public guardarJornadas: boolean = false;

  public modificarJornadas: boolean = false;

  public unEmpleado: boolean = false;

  public empleadoSolo: number = -1;

  public iteracion: number = -1;



  constructor(private formBuilder: FormBuilder, private empleadoService: EmpleadoService) { }

  ngOnInit(): void {
    this.initForm();
    this.obtenerEmpleados();
  }

  initForm(){
    this.formEmpleado = this.formBuilder.group({
      id: new FormControl ('', [Validators.required]),
      nombre: new FormControl ('', [Validators.required]),
      apellido: new FormControl ('', [Validators.required]),
      edad: new FormControl ('', [Validators.required]),
      dni: new FormControl ('', [Validators.required]),
      buscar: new FormControl ('', [Validators.required])
    });
    this.formJornada = this.formBuilder.group({
      idEmpleado: new FormControl ('', [Validators.required]),
      idJornada: new FormControl ('', [Validators.required]),
      tipo: new FormControl ('', [Validators.required]),
      fecha: new FormControl ('', [Validators.required]),
      horarioEntrada: new FormControl ('', [Validators.required]),
      horarioSalida: new FormControl ('', [Validators.required]),
      buscar: new FormControl ('', [Validators.required])
    });
  }

  limpiarFormulario(){
    this.formEmpleado.reset();
    this.formJornada.reset();
  }

  abrirGuardarEmpleado(): void{
    this.limpiarFormulario();
    this.modificarEmpleados = false;
    this.asignarJornadas = false;
    this.guardarJornadas = false;
    this.modificarJornadas = false;
    this.guardarEmpleados = true;
  }

  abrirModificarEmpleado(): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.asignarJornadas = false;
    this.guardarJornadas = false;
    this.modificarJornadas = false;
    this.modificarEmpleados = true;
  }

  abrirGuardarJornada(): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.asignarJornadas = false;
    this.modificarJornadas = false;
    this.guardarJornadas = true;
  }

  abrirAsignarJornada(): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.guardarJornadas = false;
    this.modificarJornadas = false;
    this.asignarJornadas = true;
  }

  abrirModificarJornada(): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.asignarJornadas = false;
    this.guardarJornadas = false;
    this.modificarJornadas = true;
  }

  mostrarEmpleado():void{
    this.unEmpleado = true;
    this.iteracion = -1;
    this.empleadoSolo = this.formEmpleado.get('buscar')?.value;
  }

  mostrarEmpleados():void{
    this.unEmpleado = false;
    this.iteracion = -1;
  }

  abrirModificarEmpleadoConEmpleado(empleado: Empleado): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.asignarJornadas = false;
    this.guardarJornadas = false;
    this.modificarJornadas = false;
    this.modificarEmpleados = true;
    this.formEmpleado.get('id')?.setValue(empleado.id);
    this.formEmpleado.get('nombre')?.setValue(empleado.nombre);
    this.formEmpleado.get('apellido')?.setValue(empleado.apellido);
    this.formEmpleado.get('edad')?.setValue(empleado.edad);
    this.formEmpleado.get('dni')?.setValue(empleado.dni);
    window.scroll(0,0);
  }

  abrirGuardarJornadaConEmpleado(idEmpleado: number): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.asignarJornadas = false;
    this.modificarJornadas = false;
    this.guardarJornadas = true;
    this.formJornada.get('idEmpleado')?.setValue(idEmpleado);
    window.scroll(0,0);
  }

  abrirAsignarJornadaConEmpleado(idEmpleado: number): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.guardarJornadas = false;
    this.modificarJornadas = false;
    this.asignarJornadas = true;
    this.formJornada.get('idEmpleado')?.setValue(idEmpleado);
    window.scroll(0,0);
  }

  abrirModificarJornadaConJornada(idEmpleado: number, jornada: Jornada): void{
    this.limpiarFormulario();
    this.guardarEmpleados = false;
    this.modificarEmpleados = false;
    this.asignarJornadas = false;
    this.guardarJornadas = false;
    this.modificarJornadas = true;
    this.formJornada.get('idEmpleado')?.setValue(idEmpleado);
    this.formJornada.get('idJornada')?.setValue(jornada.id);
    this.formJornada.get('tipo')?.setValue(jornada.tipo);
    this.formJornada.get('fecha')?.setValue(jornada.fecha);
    this.formJornada.get('horarioEntrada')?.setValue(jornada.horarioEntrada);
    this.formJornada.get('horarioSalida')?.setValue(jornada.horarioSalida);
    window.scroll(0,0);
  }

  mostrarJornadasDeEmpleado(i: number): void{
    if(this.iteracion < 0 || this.iteracion !== i){
        this.iteracion = i;
      } else {
        this.iteracion = -1;
      }
  }

  // mostrarJornadasDeEmpleados(i: number): void{
  //   Object.entries(this.listEmpleados[i]).forEach(([key, value], index) => {
  //       // console.log(key, value, index);
  //       if(key === 'jornada'){
  //         this.jornadas = value;
  //         if(this.jornadas.length > 0){
  //           this.tieneJornada = true;
  //         } else{
  //           this.tieneJornada = false;
  //         }
  //       }
  //   });
  // }

  crearEmpleado(): void{
    var empleado = new Empleado();
    empleado.nombre = this.formEmpleado.get('nombre')?.value;
    empleado.apellido = this.formEmpleado.get('apellido')?.value;
    empleado.edad = this.formEmpleado.get('edad')?.value;
    empleado.dni = this.formEmpleado.get('dni')?.value;

    this.empleadoService.crearEmpleado(empleado).subscribe(res => {
      this.formEmpleado.reset();
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el crear empleado.'));
  }

  modificarEmpleado(): void{
    var empleado = new Empleado();
    empleado.nombre = this.formEmpleado.get('nombre')?.value;
    empleado.apellido = this.formEmpleado.get('apellido')?.value;
    empleado.edad = this.formEmpleado.get('edad')?.value;
    empleado.dni = this.formEmpleado.get('dni')?.value;

    this.empleadoService.modificarEmpleado(this.formEmpleado.get('id')?.value, empleado).subscribe(res => {
      this.formEmpleado.reset();
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el modificar empleado.'));
  }

  eliminarEmpleado(empleadoId: any): void{
    this.empleadoService.eliminarEmpleado(empleadoId).subscribe(res => {
      console.log(res);
      this.ngOnInit();
    },
    error => console.error('Falló el eliminar empleado.'));
  }

  obtenerEmpleado(): void{
    this.empleadoService.obtenerEmpleado(this.formEmpleado.get('buscar')?.value).subscribe(res => {
      this.empleado = res;
      console.log('Se obtuvo el empleado: ' + res);
    },
    error => console.error('Falló el obtener empleado.'));
  }

  obtenerEmpleados(): void{
    this.empleadoService.obtenerEmpleados().subscribe(res => {
      this.listEmpleados = res;
      // console.log('La lista de empleados es: ');
      // Object.entries(this.listEmpleados).forEach(([key, value], index) => {
      //   console.log(key, value, index);
      // });
      console.log('La funcion de obtener todos los empleados funciona correctamente.');
    },
    error => console.error('Falló el obtener empleados.'));
  }



  public asignarJornadaYaExistenteAEmpleado(): void{
    this.empleadoService.asignarJornadaYaExistenteAEmpleado(this.formJornada.get('idEmpleado')?.value, this.formJornada.get('idJornada')?.value).subscribe(res => {
      this.formJornada.reset();
      console.log('Se asignó la jornada ya existente a un empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el asignar una jornada ya existente a un empleado.'));
  }

  public asignarJornadaNoExistenteAEmpleado(): void{
    var jornada = new Jornada();
    jornada.tipo = this.formJornada.get('tipo')?.value;
    jornada.fecha = this.formJornada.get('fecha')?.value;
    jornada.horarioEntrada = this.formJornada.get('horarioEntrada')?.value;
    jornada.horarioSalida = this.formJornada.get('horarioSalida')?.value;

    this.empleadoService.asignarJornadaNoExistenteAEmpleado(this.formJornada.get('idEmpleado')?.value, jornada).subscribe(res => {
      this.formJornada.reset();
      console.log('Se asignó la jornada no existente a un empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el asignar una jornada no existente a un empleado.'));
  }

  public modificarJornadaDeUnEmpleado(): void{
    var jornada = new Jornada();
    jornada.tipo = this.formJornada.get('tipo')?.value;
    jornada.fecha = this.formJornada.get('fecha')?.value;
    jornada.horarioEntrada = this.formJornada.get('horarioEntrada')?.value;
    jornada.horarioSalida = this.formJornada.get('horarioSalida')?.value;

    this.empleadoService.modificarJornadaDeUnEmpleado(this.formJornada.get('idEmpleado')?.value, this.formJornada.get('idJornada')?.value, jornada).subscribe(res => {
      this.formJornada.reset();
      console.log('Se modificó la jornada del empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el modificar una jornada de un empleado.'));
  }

  public desvincularUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): void{
    this.empleadoService.desvincularUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId, jornadaId).subscribe(res => {
      console.log('Se desvinculó la jornada del empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el desvincular una jornada que está dentro de un empleado.'));
  }

  public eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): void{
    this.empleadoService.eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId, jornadaId).subscribe(res => {
      console.log('Se eliminó la jornada del empleado: ' + res);
      this.ngOnInit();
    },
    error => console.error('Falló el eliminar una jornada que está dentro de un empleado.'));
  }




}
