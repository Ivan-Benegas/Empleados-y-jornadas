import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Empleado } from '../modelo/Empleado';
import { Jornada } from '../modelo/Jornada';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor(private http: HttpClient) { }

  public crearEmpleado(empleadoRequest : Empleado): Observable<any>{
    let url = environment.backend + 'saveEmpleado/';
    return this.http.post(url, empleadoRequest);
  }

  public modificarEmpleado(empleadoId : number, empleadoRequest : Empleado): Observable<any>{
    let url = environment.backend + 'updateEmpleado/' + empleadoId;
    return this.http.put(url, empleadoRequest);
  }

  public eliminarEmpleado(empleadoId : number): Observable<any>{
    let url = environment.backend + 'deleteEmpleado/' + empleadoId;
    return this.http.delete(url);
  }

  public obtenerEmpleado(empleadoId : number): Observable<Empleado>{
    let url = environment.backend + 'getEmpleado/' + empleadoId;
    return this.http.get<Empleado>(url);
  }

  public obtenerEmpleados(): Observable<Array<Empleado>>{
    let url = environment.backend + 'getAllEmpleados/';
    return this.http.get<Array<Empleado>>(url);
  }



  public asignarJornadaYaExistenteAEmpleado(empleadoId : number, jornadaId : number): Observable<any>{
    let url = environment.backend + 'assignJornadaYaExistenteAEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.put(url, '');
  }

  public asignarJornadaNoExistenteAEmpleado(empleadoId : number, jornadaRequest : Jornada): Observable<any>{
    let url = environment.backend + 'assignJornadaNoExistenteAEmpleado/' + empleadoId;
    return this.http.put(url, jornadaRequest);
  }

  public modificarJornadaDeUnEmpleado(empleadoId : number, jornadaId : number, jornadaRequest : Jornada): Observable<any>{
    let url = environment.backend + 'updateJornadaDeUnEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.put(url, jornadaRequest);
  }

  public desvincularUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): Observable<any>{
    let url = environment.backend + 'unlinkUnaJornadaQueEstaDentroDeUnEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.put(url, '');
  }

  public eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): Observable<any>{
    let url = environment.backend + 'deleteUnaJornadaQueEstaDentroDeUnEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.delete(url);
  }





}
