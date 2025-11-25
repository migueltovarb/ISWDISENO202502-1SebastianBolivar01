package com.salud360.salud360.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administradores")
public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String id, String nombre, String correo, String contrasenia) {
        super(id, nombre, correo, contrasenia);
    }
}
