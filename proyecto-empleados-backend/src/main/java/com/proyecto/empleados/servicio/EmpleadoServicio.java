package com.proyecto.empleados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.empleados.entidad.EmpleadoEntidad;
import com.proyecto.empleados.entidad.JornadaEntidad;
import com.proyecto.empleados.repositorio.EmpleadoRepositorio;
import com.proyecto.empleados.repositorio.JornadaRepositorio;
import com.proyecto.empleados.validacion.Validacion;

@Service
public class EmpleadoServicio {

	@Autowired
	EmpleadoRepositorio empleadoRepositorio;

	@Autowired
	JornadaRepositorio jornadaRepositorio;

	@Autowired
	JornadaServicio jornadaServicio;

	@Autowired
	Validacion validacion;

	@Transactional
	public void saveEmpleado(EmpleadoEntidad empleado) throws Exception {

		try {

			// Llamada a objeto especializado en validaciones.
			validacion.validateEmpleado(empleado);
			
			// Validación para ver si el DNI ya pertenece a otro empleado.
			if (empleadoRepositorio.buscarEmpleadoPorDNI(empleado.getDni()) != null) {
				throw new Exception("El DNI ya pertenece a otro empleado.");
			}

			empleadoRepositorio.save(empleado);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void updateEmpleado(Integer id, EmpleadoEntidad empleadoCambiado) throws Exception {

		try {

			EmpleadoEntidad empleadoACambiar = getEmpleado(id);

			// Llamada a objeto especializado en validaciones.
			validacion.validateEmpleado(empleadoCambiado);

			// Validación para ver si el DNI ya pertenece a otro empleado que no es el que quiere modificar.
			if (empleadoRepositorio.buscarEmpleadoPorDNI(empleadoCambiado.getDni()) != null
					&& !empleadoRepositorio.buscarEmpleadoPorDNI(empleadoCambiado.getDni()).equals(empleadoACambiar)) {
				throw new Exception("El DNI ya pertenece a otro empleado que no es el que quiere modificar.");
			}

			// Validación para ver si no ingresó algún dato distinto a los datos que ya tiene el empleado a modificar.
			if (empleadoCambiado.getNombre().equals(empleadoACambiar.getNombre())
					&& empleadoCambiado.getApellido().equals(empleadoACambiar.getApellido())
					&& empleadoCambiado.getEdad().equals(empleadoACambiar.getEdad())
					&& empleadoCambiado.getDni().equals(empleadoACambiar.getDni())) {

				throw new Exception("No ingresó algún dato distinto a los datos que ya tiene el empleado a modificar.");
			}

			empleadoACambiar.setNombre(empleadoCambiado.getNombre());
			empleadoACambiar.setApellido(empleadoCambiado.getApellido());
			empleadoACambiar.setEdad(empleadoCambiado.getEdad());
			empleadoACambiar.setDni(empleadoCambiado.getDni());

			empleadoRepositorio.save(empleadoACambiar);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void assignJornadaYaExistenteAEmpleado(Integer empleadoId, Integer jornadaId) throws Exception {

		try {

			EmpleadoEntidad empleadoEntity = getEmpleado(empleadoId);

			JornadaEntidad jornadaEntity = jornadaServicio.getJornada(jornadaId);
			
			// Validación para ver si la jornada que se quiere asignar ya se pertenece a un empleado.
			if (jornadaEntity.getEmpleadoId() != null) {
				throw new Exception("La jornada que quiere asignar ya se pertenece a un empleado.");
			}

			// Llamada a objeto especializado en validaciones. Le pasamos un booleano falso para indicar que no estamos modificando una jornada.
			validacion.validarJornadaParaAsignarAEmpleado(empleadoEntity, jornadaEntity, false);
			
			jornadaEntity.setEmpleadoId(empleadoEntity.getId());
			
			jornadaRepositorio.save(jornadaEntity);
			
			List<JornadaEntidad> jornadas = empleadoEntity.getJornadas();

			jornadas.add(jornadaEntity);

			empleadoEntity.setJornadas(jornadas);
			
			empleadoRepositorio.save(empleadoEntity);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void assignJornadaNoExistenteAEmpleado(Integer empleadoId, JornadaEntidad jornadaEntity) throws Exception {

		try {

			EmpleadoEntidad empleadoEntity = getEmpleado(empleadoId);
			
			// Llamada a objeto especializado en validaciones.
			validacion.validateJornada(jornadaEntity);

			// Llamada a objeto especializado en validaciones. Le pasamos un booleano falso para indicar que no estamos modificando una jornada.
			validacion.validarJornadaParaAsignarAEmpleado(empleadoEntity, jornadaEntity, false);

			jornadaEntity.setEmpleadoId(empleadoEntity.getId());
			
			jornadaRepositorio.save(jornadaEntity);

			List<JornadaEntidad> jornadas = empleadoEntity.getJornadas();

			jornadas.add(jornadaEntity);

			empleadoEntity.setJornadas(jornadas);

			empleadoRepositorio.save(empleadoEntity);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void updateJornadaDeUnEmpleado(Integer empleadoId, Integer jornadaId, JornadaEntidad jornadaCambiada)
			throws Exception {

		try {

			EmpleadoEntidad empleadoEntity = getEmpleado(empleadoId);

			JornadaEntidad jornadaEntity = jornadaServicio.getJornada(jornadaId);
			
			// Validacion para ver si la jornada que se quiere modifcar no se encuentra en este empleado
			if (jornadaEntity.getEmpleadoId() == null) {
				throw new Exception ("La jornada que quiere modificar no se encuentra en este empleado.");
			}

			// Validación para ver si la jornada que se quiere modificar no se encuentra en este empleado.
			if (!jornadaEntity.getEmpleadoId().equals(empleadoEntity.getId())) {
				throw new Exception("La jornada que quiere modificar no se encuentra en este empleado.");
			}
			
			// Validación para ver si no ingresó algún dato distinto a los datos que ya tiene la jornada a modificar.
			if (jornadaCambiada.getTipo().equals(jornadaEntity.getTipo())
					&& jornadaCambiada.getFecha().equals(jornadaEntity.getFecha())
					&& jornadaCambiada.getHorarioEntrada().equals(jornadaEntity.getHorarioEntrada())
					&& jornadaCambiada.getHorarioSalida().equals(jornadaEntity.getHorarioSalida())) {

				throw new Exception("No ingresó algún dato distinto a los datos que ya tiene la jornada a modificar.");
			}
			
			// Llamada a objeto especializado en validaciones.
			validacion.validateJornada(jornadaCambiada);

			jornadaEntity.setTipo(jornadaCambiada.getTipo());
			jornadaEntity.setFecha(jornadaCambiada.getFecha());
			jornadaEntity.setHorarioEntrada(jornadaCambiada.getHorarioEntrada());
			jornadaEntity.setHorarioSalida(jornadaCambiada.getHorarioSalida());

			// Llamada a objeto especializado en validaciones. Le pasamos un booleano verdadero para indicar que si estamos modificando una jornada.
			validacion.validarJornadaParaAsignarAEmpleado(empleadoEntity, jornadaEntity, true);

			jornadaRepositorio.save(jornadaEntity);

		} catch (Exception e) {
			throw e;
		}

	}

	@Transactional
	public void unlinkUnaJornadaQueEstaDentroDeUnEmpleado(Integer empleadoId, Integer jornadaId) throws Exception {

		try {

			EmpleadoEntidad empleado = getEmpleado(empleadoId);

			JornadaEntidad jornada = jornadaServicio.getJornada(jornadaId);
			
			// Validacion para ver si la jornada que se quiere modifcar no se encuentra en este empleado
			if (jornada.getEmpleadoId() == null) {
				throw new Exception ("La jornada que quiere desvincular no se encuentra en este empleado.");
			}

			// Validación para ver si la jornada que se quiere desvincular no se encuentra en este empleado.
			if (!jornada.getEmpleadoId().equals(empleado.getId())) {
				throw new Exception("La jornada que quiere desvincular no se encuentra en este empleado.");
			}
			
			jornada.setEmpleadoId(null);
			
			List<JornadaEntidad> jornadas = empleado.getJornadas();

			jornadas.remove(jornada);

			empleado.setJornadas(jornadas);

			empleadoRepositorio.save(empleado);
			
			jornadaRepositorio.save(jornada);

		} catch (Exception e) {
			throw e;
		}

	}

	@Transactional
	public void deleteUnaJornadaQueEstaDentroDeUnEmpleado(Integer empleadoId, Integer jornadaId) throws Exception {

		try {

			EmpleadoEntidad empleado = getEmpleado(empleadoId);

			JornadaEntidad jornada = jornadaServicio.getJornada(jornadaId);

			// Validación para ver si la jornada que se quiere eliminar no se encuentra en este empleado.
			if (!jornada.getEmpleadoId().equals(empleado.getId())) {
				throw new Exception("La jornada que quiere eliminar no se encuentra en este empleado.");
			}

			List<JornadaEntidad> jornadas = empleado.getJornadas();

			jornadas.remove(jornada);

			empleado.setJornadas(jornadas);

			empleadoRepositorio.save(empleado);

			jornadaRepositorio.delete(jornada);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void deleteEmpleado(Integer id) throws Exception {

		try {

			getEmpleado(id);

			empleadoRepositorio.deleteById(id);

		} catch (Exception e) {
			throw e;
		}
	}

	public EmpleadoEntidad getEmpleado(Integer id) throws Exception {

		try {

			EmpleadoEntidad empleado = empleadoRepositorio.findById(id).get();

			return empleado;

		} catch (Exception e) {
			throw new Exception("No se encontró el empleado.");
		}
	}

	public List<EmpleadoEntidad> getAllEmpleados() {

		return empleadoRepositorio.findAll();
	}

}
