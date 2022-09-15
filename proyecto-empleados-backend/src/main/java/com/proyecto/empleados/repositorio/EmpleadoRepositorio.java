package com.proyecto.empleados.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.empleados.entidad.EmpleadoEntidad;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<EmpleadoEntidad, Integer>{
	
	
	// Consulta creada para poder buscar y recibir un empleado por medio del DNI.
	
	@Query("SELECT a FROM EmpleadoEntidad a WHERE a.dni = :dni")
	public EmpleadoEntidad buscarEmpleadoPorDNI(@Param("dni") Integer dni);

}
