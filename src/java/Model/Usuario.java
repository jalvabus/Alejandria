/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author juana
 */
public class Usuario {

    private int id_Usuario;
    private Persona persona;
    private String usuario;
    private String password;
    private String tipo;

    public Usuario() {
    }

    public Usuario(int id_Usuario, Persona persona, String usuario, String password, String tipo) {
        this.id_Usuario = id_Usuario;
        this.persona = persona;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
