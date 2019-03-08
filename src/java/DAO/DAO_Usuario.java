/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class DAO_Usuario {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Usuario() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }

    private Persona getPersonaByID(int id) {
        try {
            String SQL = "select * from persona where id_persona = " + id + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            Persona persona = new Persona();
            if (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setEmail(rs.getString(2));
                persona.setPassword(rs.getString(3));
                persona.setTeleforno(rs.getString(4));
                persona.setDireccion(rs.getString(5));
                persona.setFoto(rs.getString(6));
                persona.setNombre(rs.getString(7));
                persona.setApellido_paterno(rs.getString(8));
                persona.setApellido_materno(rs.getString(9));
                return persona;
            } else {
                return null;
            }

        } catch (Exception ex) {
            System.out.println("Error $Usuarios > login : " + ex);
            return null;
        }
    }

    private Usuario getUser(String user, String pswd) {
        try {
            // Login por usuario
            String SQL = "select * from usuario where usuario = '" + user + "' and password = '" + pswd + "'";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            if (rs.next()) {
                usuario.setId_Usuario(rs.getInt(1));
                usuario.setPersona(this.getPersonaByID(rs.getInt(2)));
                usuario.setUsuario(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setTipo(rs.getString(5));
                return usuario;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error $Usuarios > login : " + ex);
            return null;
        }
    }

    public Usuario login(String email, String pss) {
        Usuario usuario = new Usuario();
        try {
            // Login por usuario
            String SQL = "select * from persona where email ='" + email + "';";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = this.getUser(email, pss);
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return null;
        }
    }
}
