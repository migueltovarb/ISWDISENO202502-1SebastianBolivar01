package com.salud360.salud360.repository;

import com.salud360.salud360.models.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String> {
    Optional<Administrador> findByCorreo(String correo);
}