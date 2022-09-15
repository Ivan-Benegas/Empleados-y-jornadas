package com.proyecto.empleados.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.empleados.entidad.EmpleadoEntidad;
import com.proyecto.empleados.entidad.JornadaEntidad;
import com.proyecto.empleados.servicio.EmpleadoServicio;

@RestController
public class EmpleadoControlador {

	@Autowired
	private EmpleadoServicio empleadoServicio;
	
	private static final ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/saveEmpleado")
	public ResponseEntity<String> saveEmpleado(@RequestBody EmpleadoEntidad empleado) {

		try {

			empleadoServicio.saveEmpleado(empleado);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se guardó el empleado correctamente."));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping(value = "/updateEmpleado/{id}")
	public ResponseEntity<String> updateEmpleado(@PathVariable("id") Integer id, @RequestBody EmpleadoEntidad empleado) {

		try {
			
			empleadoServicio.updateEmpleado(id, empleado);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se modificó el empleado correctamente."));
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping(value = "/assignJornadaYaExistenteAEmpleado/{empleadoId}/{jornadaId}")
	public ResponseEntity<String> assignJornadaYaExistenteAEmpleado(@PathVariable("empleadoId") Integer empleadoId, @PathVariable("jornadaId") Integer jornadaId) {

		try {
			
			empleadoServicio.assignJornadaYaExistenteAEmpleado(empleadoId, jornadaId);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se asignó la jornada a el empleado correctamente."));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping(value = "/assignJornadaNoExistenteAEmpleado/{empleadoId}")
	public ResponseEntity<String> assignJornadaNoExistenteAEmpleado(@PathVariable("empleadoId") Integer empleadoId, @RequestBody JornadaEntidad jornada) {

		try {

			empleadoServicio.assignJornadaNoExistenteAEmpleado(empleadoId, jornada);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se creó y asignó la jornada a el empleado correctamente."));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/updateJornadaDeUnEmpleado/{empleadoId}/{jornadaId}")
	public ResponseEntity<String> updateJornadaDeUnEmpleado(@PathVariable("empleadoId") Integer empleadoId, @PathVariable("jornadaId") Integer jornadaId, @RequestBody JornadaEntidad jornada) {
		
		try {

			empleadoServicio.updateJornadaDeUnEmpleado(empleadoId, jornadaId, jornada);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se modificó la jornada del empleado correctamente."));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	@PutMapping(value = "/unlinkUnaJornadaQueEstaDentroDeUnEmpleado/{empleadoId}/{jornadaId}")
	public ResponseEntity<String> unlinkUnaJornadaQueEstaDentroDeUnEmpleado(@PathVariable("empleadoId") Integer empleadoId, @PathVariable("jornadaId") Integer jornadaId) {

		try {
			
			empleadoServicio.unlinkUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId, jornadaId);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se desvinculó la jornada del empleado correctamente."));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/deleteUnaJornadaQueEstaDentroDeUnEmpleado/{empleadoId}/{jornadaId}")
	public ResponseEntity<String> deleteUnaJornadaQueEstaDentroDeUnEmpleado(@PathVariable("empleadoId") Integer empleadoId, @PathVariable("jornadaId") Integer jornadaId) {

		try {

			empleadoServicio.deleteUnaJornadaQueEstaDentroDeUnEmpleado(empleadoId, jornadaId);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se elminó la jornada del empleado correctamente."));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/deleteEmpleado/{id}")
	public ResponseEntity<String> deleteEmpleado(@PathVariable("id") Integer id) {
		
		try {

			empleadoServicio.deleteEmpleado(id);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se borró el empleado correctamente."));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/getEmpleado/{id}")
	public ResponseEntity<Object> getEmpleado(@PathVariable("id") Integer id) {
		
		try {
			
			EmpleadoEntidad empleadoEntity = empleadoServicio.getEmpleado(id);
			
			return ResponseEntity.ok(empleadoEntity);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/getAllEmpleados")
	public ResponseEntity<Object> getAllEmpleados() {
		
		try {
			
			List<EmpleadoEntidad> listEmpleadoEntity = empleadoServicio.getAllEmpleados();
			
			return ResponseEntity.ok(listEmpleadoEntity);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
