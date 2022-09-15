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
import com.proyecto.empleados.entidad.JornadaEntidad;
import com.proyecto.empleados.servicio.JornadaServicio;

@RestController
public class JornadaControlador {

	@Autowired
	private JornadaServicio jornadaServicio;
	
	private static final ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/saveJornada")
	public ResponseEntity<String> saveJornada(@RequestBody JornadaEntidad jornada) {
		
		try {
			
			jornadaServicio.saveJornada(jornada);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se guardó la jornada correctamente."));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/updateJornada/{id}")
	public ResponseEntity<String> updateJornada(@PathVariable("id") Integer id, @RequestBody JornadaEntidad jornada) {

		try {
			
			jornadaServicio.updateJornada(id, jornada);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se modificó la jornada correctamente."));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/deleteJornada/{id}")
	public ResponseEntity<String> deleteJornada(@PathVariable("id") Integer id) {
		
		try {
			
			jornadaServicio.deleteJornada(id);
			
			return ResponseEntity.ok(mapper.writeValueAsString("Se borró la jornada correctamente."));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/getJornada/{id}")
	public ResponseEntity<Object> getJornada(@PathVariable("id") Integer id) {
		
		try {
			
			JornadaEntidad jornadaEntity = jornadaServicio.getJornada(id);
			
			return ResponseEntity.ok(jornadaEntity);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/getAllJornadas")
	public ResponseEntity<Object> getAllJornadas() {
		
		try {
			
			List<JornadaEntidad> listJornadaEntity = jornadaServicio.getAllJornadas();
			
			return ResponseEntity.ok(listJornadaEntity);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
