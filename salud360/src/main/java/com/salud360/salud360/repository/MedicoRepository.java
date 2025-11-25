package com.salud360.salud360.repository;

import com.salud360.salud360.models.Medico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends MongoRepository<Medico, String> {
    List<Medico> findByEspecialidad(String especialidad);
    Optional<Medico> findByCorreo(String correo);
}