package com.proyecto.empleados.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.empleados.entidad.JornadaEntidad;

@Repository
public interface JornadaRepositorio extends JpaRepository<JornadaEntidad, Integer> {

}
