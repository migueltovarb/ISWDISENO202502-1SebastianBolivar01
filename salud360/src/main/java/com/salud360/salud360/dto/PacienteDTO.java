package com.salud360.salud360.dto;

import jakarta.validation.constraints.*;

public class PacienteDTO {
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasenia;

    private Integer totalCitas;

    public PacienteDTO() {
    }

    public PacienteDTO(String id, String nombre, String correo, String contrasenia, Integer totalCitas) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.totalCitas = totalCitas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getTotalCitas() {
        return totalCitas;
    }

    public void setTotalCitas(Integer totalCitas) {
        this.totalCitas = totalCitas;
    }
}