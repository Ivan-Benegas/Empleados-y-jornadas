package com.proyecto.empleados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.empleados.entidad.JornadaEntidad;
import com.proyecto.empleados.repositorio.JornadaRepositorio;
import com.proyecto.empleados.validacion.Validacion;

@Service
public class JornadaServicio {

	@Autowired
	private JornadaRepositorio jornadaRepositorio;

	@Autowired
	@Lazy
	EmpleadoServicio empleadoServicio;

	@Autowired
	Validacion validacion;

	@Transactional
	public void saveJornada(JornadaEntidad jornada) throws Exception {

		try {

			// Llamada a objeto especializado en validaciones.
			validacion.validateJornada(jornada);

			jornadaRepositorio.save(jornada);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void updateJornada(Integer id, JornadaEntidad jornadaCambiada) throws Exception {

		try {

			JornadaEntidad jornadaEntity = getJornada(id);

			//Validacion para ver si la jornada que se quiere modificar pertenece a un empleado.
			if (jornadaEntity.getEmpleadoId() != null) {

				throw new Exception(
						"Esta jornada pertence a un empleado. Por favor, utilice 'Update Jornada De Un Empleado' para modificar una jornada que pertenece a un empleado.");
			}

			// Llamada a objeto especializado en validaciones.
			validacion.validateJornada(jornadaCambiada);

			// Validación para ver si no ingresó algún dato distinto a los datos que ya
			// tiene la jornada a modificar.
			if (jornadaCambiada.getTipo().equals(jornadaEntity.getTipo())
					&& jornadaCambiada.getFecha().equals(jornadaEntity.getFecha())
					&& jornadaCambiada.getHorarioEntrada().equals(jornadaEntity.getHorarioEntrada())
					&& jornadaCambiada.getHorarioSalida().equals(jornadaEntity.getHorarioSalida())) {

				throw new Exception("No ingresó algún dato distinto a los datos que ya tiene la jornada a modificar.");
			}

			jornadaEntity.setTipo(jornadaCambiada.getTipo());
			jornadaEntity.setFecha(jornadaCambiada.getFecha());
			jornadaEntity.setHorarioEntrada(jornadaCambiada.getHorarioEntrada());
			jornadaEntity.setHorarioSalida(jornadaCambiada.getHorarioSalida());

			jornadaRepositorio.save(jornadaEntity);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void deleteJornada(Integer id) throws Exception {

		try {

			JornadaEntidad jornadaEntity = getJornada(id);

			//Validacion para ver si la jornada que se quiere eliminar pertenece a un empleado.
			if (jornadaEntity.getEmpleadoId() != null) {

				throw new Exception(
						"Esta jornada pertence a un empleado. Por favor, utilice 'Delete Una Jornada Que Esta Dentro De Un Empleado' para eliminar una jornada que pertenece a un empleado.");
			}

			jornadaRepositorio.deleteById(id);

		} catch (Exception e) {
			throw e;
		}
	}

	public JornadaEntidad getJornada(Integer id) throws Exception {

		try {

			JornadaEntidad jornada = jornadaRepositorio.findById(id).get();

			return jornada;

		} catch (Exception e) {
			throw new Exception("No se encontró la jornada.");
		}
	}

	public List<JornadaEntidad> getAllJornadas() {

		return jornadaRepositorio.findAll();
	}

}
