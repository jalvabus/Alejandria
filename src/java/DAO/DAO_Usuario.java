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
import java.util.ArrayList;

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

    public Persona getPersonaByID(int id) {
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

    public Usuario getUserByID(int id_usuario) {
        try {
            // Login por usuario
            String SQL = "select * from usuario where id_usuario = " + id_usuario + ";";
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

    public Usuario getOneByCorreo(String correo) {
        try {
            // Login por usuario
            String SQL = "select * from usuario where usuario = '" + correo + "';";
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

    public Usuario getUser(String user, String pswd) {
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

    public Tarjeta_Prepago getDatosTarjetaByIDPersona(int id_persona, String numero) {
        Tarjeta_Prepago tarjeta = new Tarjeta_Prepago();
        try {
            // Login por usuario
            String SQL = "select * from tarjeta_prepago where id_persona = " + id_persona + " and numero = " + numero + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tarjeta.setId_tarjeta(rs.getInt(1));
                tarjeta.setNumero(rs.getInt(2));
                tarjeta.setEstado(rs.getString(3));
                tarjeta.setSaldo(rs.getFloat(4));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                tarjeta.setPersona(dao_usuario.getPersonaByID(rs.getInt(5)));
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return null;
        }
        return tarjeta;
    }

    public Saldo_persona getSaldoByIdPersona(int id_persona) {
        Saldo_persona saldo = new Saldo_persona();
        try {
            String SQL = "select * from saldo_persona where id_persona = " + id_persona + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldo.setId_saldo(rs.getInt(1));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                saldo.setPersona(dao_usuario.getPersonaByID(rs.getInt(2)));
                saldo.setSaldo(rs.getFloat(3));
                saldo.setPuntos(rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return null;
        }
        return saldo;
    }
}
