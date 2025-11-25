package com.salud360.salud360.repository;


import com.salud360.salud360.models.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
    Optional<Paciente> findByCorreo(String correo);
}
