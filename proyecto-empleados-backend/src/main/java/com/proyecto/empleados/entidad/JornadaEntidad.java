package com.proyecto.empleados.entidad;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.proyecto.empleados.enumerado.JornadaTipo;

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
public class JornadaEntidad {
	
	@Id
	@GeneratedValue(generator = "uuid")
	private Integer id;
	private JornadaTipo tipo;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate fecha;
	private Integer horarioEntrada;
	private Integer horarioSalida;
	private Integer empleadoId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	public LocalDate getFecha() {
		return fecha;
	}
	
}
