package com.proyecto.empleados.validacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.proyecto.empleados.entidad.EmpleadoEntidad;
import com.proyecto.empleados.entidad.JornadaEntidad;
import com.proyecto.empleados.enumerado.JornadaTipo;
import com.proyecto.empleados.servicio.EmpleadoServicio;

@Service
public class Validacion {

	@Autowired
	@Lazy
	EmpleadoServicio empleadoServicio;

	// Validaciones de la entidad Empleado.
	public void validateEmpleado(EmpleadoEntidad empleado) throws Exception {

		try {
			
			// Validación para ver si el nombre está vacío.
			if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
				throw new Exception("El nombre no puede estar vacío.");
			}
			
			// Validación para ver si el nombre solo contiene letras en mayúsculas y minúsculas y espacios en blanco.
			if (!empleado.getNombre().matches("^[ A-Za-z]+$")) {
				throw new Exception(
						"El nombre solo puede contener letras en mayúsculas y minúsculas y espacios en blanco.");
			}
			
			// Validación para ver si el apellido está vacío.
			if (empleado.getApellido() == null || empleado.getApellido().trim().isEmpty()) {
				throw new Exception("El apellido no puede estar vacío.");
			}
			
			// Validación para ver si el apellido solo contiene letras en mayúsculas y minúsculas y espacios en blanco.
			if (!empleado.getApellido().matches("^[ A-Za-z]+$")) {
				throw new Exception(
						"El apellido solo puede contener letras en mayúsculas y minúsculas y espacios en blanco.");
			}
			
			// Validación para ver si la edad está vacía.
			if (empleado.getEdad() == null) {
				throw new Exception("La edad no puede estar vacía.");
			}
			
			// Validación para ver si la edad es menor a 18.
			if (empleado.getEdad() < 18) {
				throw new Exception("La edad no puede ser menor a 18 años.");
			}
			
			// Validación para ver si la edad es mayor a 100.
			if (empleado.getEdad() > 100) {
				throw new Exception("La edad no puede ser mayor a 100 años.");
			}
			
			// Validación para ver si el DNI está vacío.
			if (empleado.getDni() == null) {
				throw new Exception("El DNI no puede estar vacío.");
			}
			
			// Validación para ver si el DNI contiene menos de 8 dígitos.
			if (String.valueOf(empleado.getDni()).length() < 8) {
				throw new Exception("El DNI no puede tener menos de 8 dígitos.");
			}
			
			// Validación para ver si el DNI contiene más de 8 dígitos.
			if (String.valueOf(empleado.getDni()).length() > 8) {
				throw new Exception("El DNI no puede tener más de 8 dígitos.");
			}

		} catch (Exception e) {
			throw e;
		}
	}
	
	// Validaciones de la entidad Jornada.
	public void validateJornada(JornadaEntidad jornada) throws Exception {

		try {
			
			// Validación para ver si el tipo de jornada está vacío.
			if (jornada.getTipo() == null) {
				throw new Exception("No puede ingresar el tipo de jornada vacío.");
			}
			
			// Validación para ver si se ingresó un tipo de jornada válido.
			if (!jornada.getTipo().equals(JornadaTipo.TURNO_NORMAL) && jornada.getTipo().equals(JornadaTipo.TURNO_EXTRA)
					&& jornada.getTipo().equals(JornadaTipo.DIA_LIBRE)
					&& jornada.getTipo().equals(JornadaTipo.VACACIONES)) {
				throw new Exception("No ingresó un tipo de jornada válido.");
			}
			
			// Validación que, si el tipo de jornada es "dia libre" o "vacaciones", configura el horario de entrada y de salida a 0.
			if (jornada.getTipo() == JornadaTipo.DIA_LIBRE || jornada.getTipo() == JornadaTipo.VACACIONES) {
				jornada.setHorarioEntrada(0);
				jornada.setHorarioSalida(0);
			}
			
			// Validación para ver si la fecha está vacía.
			if (jornada.getFecha() == null) {
				throw new Exception("No puede ingresar la fecha vacía.");
			}
			
			// Validación para ver si se ingresó un año entre 1900 y 2030.
			if (jornada.getFecha().getYear() < 1900 || jornada.getFecha().getYear() > 2030) {
				throw new Exception(
						"No ingresó el número del año de la fecha de forma válida. Recuerde que este programa acepta años del 1900 al 2030.");
			}
			
			// Validación para ver si la fecha ingresada corresponde a un sábado.
			if (jornada.getFecha().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				throw new Exception("No puede ingresar una jornada laboral en un sábado.");
			}
			
			// Validación para ver si la fecha ingresada corresponde a un domingo.
			if (jornada.getFecha().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				throw new Exception("No puede ingresar una jornada laboral en un domingo.");
			}
			
			// Validación para ver si el horario de entrada está vacío.
			if (jornada.getHorarioEntrada() == null) {
				throw new Exception("No puede ingresar el horario de entrada vacío.");
			}
			
			// Validación para ver si se ingresó un horario de entrada entre 0 y 23.
			if (jornada.getHorarioEntrada() < 0 || jornada.getHorarioEntrada() > 23) {
				throw new Exception(
						"No ingresó una hora de entrada válida. Recuerde que este programa acepta horas del 0 al 23.");
			}
			
			// Validación para ver si el horario de salida está vacío.
			if (jornada.getHorarioSalida() == null) {
				throw new Exception("No puede ingresar el horario de salida vacío.");
			}
			
			// Validación para ver si se ingresó un horario de salida entre 0 y 23.
			if (jornada.getHorarioSalida() < 0 || jornada.getHorarioSalida() > 23) {
				throw new Exception(
						"No ingresó una hora de salida válida. Recuerde que este programa acepta horas del 0 al 23.");
			}
			
			// Validación para ver si el horario de entrada está antes que el horario de salida.
			if (jornada.getHorarioEntrada() > jornada.getHorarioSalida()) {
				throw new Exception("La hora de entrada no puede ser después de la hora de salida.");
			}
			
			// Validación para ver que en el caso de que se ingrese una jornada de tipo "turno normal"
			if (jornada.getTipo() == JornadaTipo.TURNO_NORMAL) {
				
				// que la cantidad de horas no sea menor a 6 horas
				if (jornada.getHorarioSalida() - jornada.getHorarioEntrada() < 6) {
					throw new Exception(
							"Una jornada de tipo 'turno normal' no puede tener menos de 6 horas trabajadas.");
				}
				
				// y mayor a 8 horas.
				if (jornada.getHorarioSalida() - jornada.getHorarioEntrada() > 8) {
					throw new Exception("Una jornada de tipo 'turno normal' no puede tener más de 8 horas trabajadas.");
				}

			}
			
			// Validación para ver que en el caso de que se ingrese una jornada de tipo "turno extra"
			if (jornada.getTipo() == JornadaTipo.TURNO_EXTRA) {
				
				// que la cantidad de horas no sea menor a 2 horas
				if (jornada.getHorarioSalida() - jornada.getHorarioEntrada() < 2) {
					throw new Exception(
							"Una jornada de tipo 'turno extra' no puede tener menos de 2 horas trabajadas.");
				}
				
				// y mayor a 6 horas.
				if (jornada.getHorarioSalida() - jornada.getHorarioEntrada() > 6) {
					throw new Exception("Una jornada de tipo 'turno extra' no puede tener más de 6 horas trabajadas.");
				}

			}

		} catch (Exception e) {
			throw e;
		}

	}
	// Validaciones para cuando se asigna o modifica una jornada a un empleado.
	public void validarJornadaParaAsignarAEmpleado(EmpleadoEntidad empleadoEntity, JornadaEntidad jornadaEntity,
			boolean update) throws Exception {

		try {
			
			// Recorremos todas las jornadas que tiene el empleado el cual yo quiero agregar o modificar una jornada.
			for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {
				
				// Validamos si la fecha de la jornada que estamos actualmente recorriendo coincide con la fecha de la jornada que se quiere asignar o modificar.
				if (jornada.getFecha().equals(jornadaEntity.getFecha())) {

					// Validación para ver si la jornada que estamos actualmente recorriendo es de tipo "día libre" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.DIA_LIBRE && !update) {

						throw new Exception("No se puede agregar otra jornada en un día libre.");
					}
					
					// Validación para ver si la jornada que estamos actualmente recorriendo es de tipo "vacaciones" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.VACACIONES && !update) {

						throw new Exception("No se puede agregar otra jornada en un día de vacaciones.");
					}
					
					// Validación para ver si la jornada que estamos actualmente recorriendo es de tipo "turno normal", si la jornada que se quiere asignar o modificar es de tipo "turno normal" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_NORMAL
							&& jornadaEntity.getTipo() == JornadaTipo.TURNO_NORMAL && !update) {

						throw new Exception(
								"No se puede agregar un turno normal en un día que ya tiene un turno normal.");
					}
					
					// Validación para ver si la jornada que estamos actualmente recorriendo es de tipo "turno extra", si la jornada que se quiere asignar o modificar es de tipo "turno extra" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_EXTRA
							&& jornadaEntity.getTipo() == JornadaTipo.TURNO_EXTRA && !update) {

						throw new Exception(
								"No se puede agregar un turno extra en un día que ya tiene un turno extra.");
					}
					
					// Validación para ver si la jornada que estamos recorriendo actualmente es de tipo "turno normal" y si la jornada que se quiere asignar o modificar es de tipo "turno extra"
					// o si la jornada que estamos recorriendo actualmente es de tipo "turno extra" y si la jornada que se quiere asignar o modificar es de tipo "turno normal".
					if ((jornada.getTipo() == JornadaTipo.TURNO_NORMAL
							&& jornadaEntity.getTipo() == JornadaTipo.TURNO_EXTRA)
							|| (jornada.getTipo() == JornadaTipo.TURNO_EXTRA
									&& jornadaEntity.getTipo() == JornadaTipo.TURNO_NORMAL)) {
						
						// Validación para ver si la suma entre las horas que tiene la jornada que estamos recorriendo actualmente y las horas de la jornada que se quiere asignar o modificar es mayor que 12.
						if ((jornada.getHorarioSalida() - jornada.getHorarioEntrada())
								+ (jornadaEntity.getHorarioSalida() - jornadaEntity.getHorarioEntrada()) > 12) {

							throw new Exception(
									"La suma total de horas en un día de un empleado que tiene un turno normal y un turno extra no puede superar las 12 horas.");
						}

					}

					// Validación para ver si la jornada que estamos recorriendo actualmente es de tipo "turno normal", si la jornada que se quiere asignar o modificar es de tipo "vacaciones" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_NORMAL
							&& jornadaEntity.getTipo() == JornadaTipo.VACACIONES && !update) {

						throw new Exception(
								"No se puede agregar una día de vacaciones a un día que ya tiene un turno normal.");

					}

					// Validación para ver si la jornada que estamos recorriendo actualmente es de tipo "turno normal", si la jornada que se quiere asignar o modificar es de tipo "día libre" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_NORMAL
							&& jornadaEntity.getTipo() == JornadaTipo.DIA_LIBRE && !update) {

						throw new Exception("No se puede agregar una día libre a un día que ya tiene un turno normal.");

					}

					// Validación para ver si la jornada que estamos recorriendo actualmente es de tipo "turno extra", si la jornada que se quiere asignar o modificar es de tipo "vacaciones" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_EXTRA
							&& jornadaEntity.getTipo() == JornadaTipo.VACACIONES && !update) {

						throw new Exception(
								"No se puede agregar una día de vacaciones a un día que ya tiene un turno extra.");

					}

					// Validación para ver si la jornada que estamos recorriendo actualmente es de tipo "turno extra", si la jornada que se quiere asignar o modificar es de tipo "día libre" y si estamos modificando o no una jornada.
					if (jornada.getTipo() == JornadaTipo.TURNO_EXTRA && jornadaEntity.getTipo() == JornadaTipo.DIA_LIBRE
							&& !update) {

						throw new Exception("No se puede agregar una día libre a un día que ya tiene un turno extra.");
					}
				}
			}

			// Creo un lista que va a contener jornadas. Las jornadas que se van a ir agregando a dicha lista tienen que pertenecer a la misma semana a la cual pertenece la jornada que se quiere asignar o modificar.
			List<JornadaEntidad> semana = new ArrayList<JornadaEntidad>();
			
			// Creo un switch ir agregando las jornadas a la lista independientemente del día de la semana que tiene la jornada que quiero asginar o modificar.
			// Voy a explicar el paso a paso del primer caso nada más porque los demás siguen la misma lógica.
			// Este switch solo corrobora los dias lunes, martes, miércoles, jueves y viernes ya que mi programa no permite agregar una jornada en los días sábados y domingos.
			switch (jornadaEntity.getFecha().getDayOfWeek()) {
			// El caso de que el día de la semana que tiene la jornada que quiero asignar o modificar sea un lunes.
			case MONDAY:
				
				// Creo una variable de tipo LocalDate para almacenar la fecha que tiene el último domingo que hubo hasta la fecha que tiene la jornada que quiero asignar o modificar.
				// Al ser un lunes, sé que el domingo es un día antes.
				LocalDate domingo1 = jornadaEntity.getFecha().minusDays(1);

				// Creo una variable de tipo LocalDate para almacenar la fecha que tiene el próximo sábado inmediato que viene después de la fecha que tiene la jornada que quiero asignar o modificar.
				// Al ser un lunes, sé que el sábado son 5 días después.
				LocalDate sabado1 = jornadaEntity.getFecha().plusDays(5);

				// Creo un for each para recorrer todas las jornadas que tiene el empleado el cual yo quiero agregar o modificar una jornada.
				for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {

					// Validación para ver si la jornada que estoy recorriendo es después del domingo y antes del sábado.
					if (jornada.getFecha().isAfter(domingo1) && jornada.getFecha().isBefore(sabado1)) {

						// En caso de que así sea, agrego la jornada a la lista de jornadas.
						semana.add(jornada);
					}
				}
				break;
			case TUESDAY:

				LocalDate domingo2 = jornadaEntity.getFecha().minusDays(2);

				LocalDate sabado2 = jornadaEntity.getFecha().plusDays(4);

				for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {

					if (jornada.getFecha().isAfter(domingo2) && jornada.getFecha().isBefore(sabado2)) {

						semana.add(jornada);
					}
				}
				break;
			case WEDNESDAY:

				LocalDate domingo3 = jornadaEntity.getFecha().minusDays(3);

				LocalDate sabado3 = jornadaEntity.getFecha().plusDays(3);

				for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {

					if (jornada.getFecha().isAfter(domingo3) && jornada.getFecha().isBefore(sabado3)) {

						semana.add(jornada);
					}
				}
				break;
			case THURSDAY:

				LocalDate domingo4 = jornadaEntity.getFecha().minusDays(4);

				LocalDate sabado4 = jornadaEntity.getFecha().plusDays(2);

				for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {

					if (jornada.getFecha().isAfter(domingo4) && jornada.getFecha().isBefore(sabado4)) {

						semana.add(jornada);
					}
				}
				break;
			case FRIDAY:

				LocalDate domingo5 = jornadaEntity.getFecha().minusDays(5);

				LocalDate sabado5 = jornadaEntity.getFecha().plusDays(1);

				for (JornadaEntidad jornada : empleadoEntity.getJornadas()) {

					if (jornada.getFecha().isAfter(domingo5) && jornada.getFecha().isBefore(sabado5)) {

						semana.add(jornada);
					}
				}
				break;
			default:
				throw new Exception("El switch de día de la semana arrojó error.");
			}
			
			// Creo un contador de días libres que va a almacenar estos presentes en la semana.
			Integer contadorDiasLibres = 0;

			// Creo un contador de días de vacaciones que va a almacenar estos presentes en la semana.
			Integer contadorDiasVacaciones = 0;

			// Creo un contador de horas semanales.
			Integer contadorHorasSemanales = 0;

			// Creo un HashSet de tipo DayOfWeek que es de tipo Enum que contiene los valores de los días de semana (lunes, martes, miércoles, jueves y viernes) para guardar los que estan presentes en la semana.
			HashSet<DayOfWeek> diasPresentesEnLaSemana = new HashSet<DayOfWeek>();

			// Recorro todas las jornadas presentes en la lista de jornadas que se llama semana que armé anteriormente.
			for (JornadaEntidad jornada : semana) {

				// Si la jornada que estamos recorriendo es de tipo "día libre", sumamos 1 al contador de días libres.
				if (jornada.getTipo() == JornadaTipo.DIA_LIBRE) {

					contadorDiasLibres++;
				}
				
				// Si la jornada que estamos recorriendo es de tipo "vacaciones", sumamos 1 al contador de vacaciones.
				if (jornada.getTipo() == JornadaTipo.VACACIONES) {

					contadorDiasVacaciones++;
				}

				// Agregamos el día de la semana presente en la fecha de la jornada que estamos recorriendo al HashSet.
				// Como es un HashSet, no va a guardar días repetidos aunque hayan 2 jornadas en un mismo día.
				diasPresentesEnLaSemana.add(jornada.getFecha().getDayOfWeek());

				// Va contando las horas de las jornadas de toda la semana.
				contadorHorasSemanales += jornada.getHorarioSalida() - jornada.getHorarioEntrada();

			}

			// Valido si la jornada que quiero asignar o modificar es de tipo "día libre" y el contador de días libre es mayor a 1.
			if (jornadaEntity.getTipo() == JornadaTipo.DIA_LIBRE && contadorDiasLibres > 1) {

				throw new Exception(
						"La semana ya tiene 2 días libres. No puede agregar otro día libre más a la misma semana.");
			}

			// Valido si la jornada que quiero asignar o modificar es de tipo "turno normal" o "turno extra".
			if (jornadaEntity.getTipo() == JornadaTipo.TURNO_NORMAL
					|| jornadaEntity.getTipo() == JornadaTipo.TURNO_EXTRA) {
				
				// Valido si el contador de días libres y el de días vacaciones valen los dos 0.
				if (contadorDiasLibres == 0 && contadorDiasVacaciones == 0) {
					
					// Valido si el contador de horas semanales más las horas de la jornada que quiero asignar o modificar es mayor a 48.
					if (contadorHorasSemanales
							+ (jornadaEntity.getHorarioSalida() - jornadaEntity.getHorarioEntrada()) > 48) {

						throw new Exception("El empleado no puede trabajar más de 48 horas semanales. Quizás deba crear, modificar o eliminar alguna o algunas jornadas.");
					}

					// Valido si días presentes en la semana es igual a 4 y si el contador de horas semanales más las horas de la jornada que quiero asignar o modificar es menor a 30.
					if (diasPresentesEnLaSemana.size() == 4 && contadorHorasSemanales
							+ (jornadaEntity.getHorarioSalida() - jornadaEntity.getHorarioEntrada()) < 30) {

						throw new Exception("El empleado no puede trabajar menos de 30 horas semanales. Quizás deba crear, modificar o eliminar alguna o algunas jornadas.");
					}
				}
				
				// Creo una lista de valor EmpleadoEntity para guardar empleados y guardo todos los que hayan sido cargados.
				List<EmpleadoEntidad> listaDeEmpleados = empleadoServicio.getAllEmpleados();

				// Creo un contador de empleados por turno específico de una fecha determinada.
				Integer empleadosPorTurnoEspecificoDeUnaFechaDeterminada = 0;

				// Recorro todos los empleados de la lista de empleados.
				for (EmpleadoEntidad empleado : listaDeEmpleados) {

					// Recorro todas las jornadas del empleado que estoy recorriendo actualmente.
					for (JornadaEntidad jornada : empleado.getJornadas()) {

						// Valido si la fecha de la jornada que estoy recorriendo es igual a la fecha de la jornada que quiero asignar o modificar.
						if (jornada.getFecha().equals(jornadaEntity.getFecha())) {

							// Valido si el tipo de jornada de la jornada que estoy recorriendo es igual al tipo de jornada de la jornada que quiero asignar o modificar. 
							if (jornada.getTipo() == jornadaEntity.getTipo()) {

								// Sumo 1 al contador de empleados por turno específico de una fecha determinada.
								empleadosPorTurnoEspecificoDeUnaFechaDeterminada++;
								break;
							}
						}
					}
				}

				// Valido si el contador de empleados por turno específico de una fecha determinada es mayor a 1.
				if (empleadosPorTurnoEspecificoDeUnaFechaDeterminada > 1) {

					throw new Exception(
							"Solo puede haber 2 empleados como máximo que tengan los 2 un turno normal o un turno extra en un mismo día.");
				}
			}

		} catch (Exception e) {
			throw e;
		}

	}

}
