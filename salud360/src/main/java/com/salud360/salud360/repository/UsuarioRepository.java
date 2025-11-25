package com.salud360.salud360.repository;

import com.salud360.salud360.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByCorreoAndContrasenia(String correo, String contrasenia);
    boolean existsByCorreo(String correo);
}
