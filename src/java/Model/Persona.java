/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hp
 */
public class Persona {

    private int idPersona;
    private String email;
    private String password;
    private String teleforno;
    private String direccion;
    private String foto;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;

    public Persona() {
    }

    public Persona(int idPersona, String email, String password, String teleforno, String direccion, String foto, String nombre, String apellido_paterno, String apellido_materno) {
        this.idPersona = idPersona;
        this.email = email;
        this.password = password;
        this.teleforno = teleforno;
        this.direccion = direccion;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeleforno() {
        return teleforno;
    }

    public void setTeleforno(String teleforno) {
        this.teleforno = teleforno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

}
