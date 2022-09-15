package com.proyecto.empleados.entidad;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadoEntidad {
	
	@Id
	@GeneratedValue(generator = "uuid")
	private Integer id;
	private String nombre;
	private String apellido;
	private Integer edad;
	private Integer dni;
	@OneToMany
	private List<JornadaEntidad> jornadas;
	
}
