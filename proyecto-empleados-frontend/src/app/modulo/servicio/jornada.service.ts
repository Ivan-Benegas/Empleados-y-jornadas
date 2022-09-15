import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jornada } from '../modelo/Jornada';

@Injectable({
  providedIn: 'root'
})
export class JornadaService {

  constructor(private http: HttpClient) { }

  public crearJornada(jornadaRequest : Jornada): Observable<any>{
    let url = environment.backend + 'saveJornada/';
    return this.http.post(url, jornadaRequest);
  }

  public modificarJornada(jornadaId : number, jornadaRequest : Jornada): Observable<any>{
    let url = environment.backend + 'updateJornada/' + jornadaId;
    return this.http.put(url, jornadaRequest);
  }

  public eliminarJornada(jornadaId : number): Observable<any>{
    let url = environment.backend + 'deleteJornada/' + jornadaId;
    return this.http.delete(url);
  }

  public obtenerJornada(jornadaId : number): Observable<Jornada>{
    let url = environment.backend + 'getJornada/' + jornadaId;
    return this.http.get<Jornada>(url);
  }

  public obtenerJornadas(): Observable<Array<Jornada>>{
    let url = environment.backend + 'getAllJornadas/';
    return this.http.get<Array<Jornada>>(url);
  }



  public modificarJornadaDeUnEmpleado(empleadoId : number, jornadaId : number, jornadaRequest : Jornada): Observable<any>{
    let url = environment.backend + 'updateJornadaDeUnEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.put(url, jornadaRequest);
  }

  public eliminarUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId : number, jornadaId : number): Observable<any>{
    let url = environment.backend + 'deleteUnaJornadaQueEstaDentroDeUnEmpleado/' + empleadoId + '/' + jornadaId;
    return this.http.delete(url);
  }

}
