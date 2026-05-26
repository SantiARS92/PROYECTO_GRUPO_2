package com.example.proyecto_grupo_2;

public class Usuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String ciudad;
    private String enfermedad;
    private String descripcion;
    private String email;
    private String telefono;

    public Usuario(int id, String nombre,
                   String apellidos,
                   String ciudad,
                   String enfermedad,
                   String descripcion,
                   String email,
                   String telefono) {

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.enfermedad = enfermedad;
        this.descripcion = descripcion;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
}