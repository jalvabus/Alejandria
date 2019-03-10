/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import java.sql.Connection;
import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Juan
 */
public class DAO_Autor {
    DB_Manager db_manager;
    Connection connection;

    public DAO_Autor() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }
    
    public Autor getAutorByID(int id_autor) {
        Autor autor = new Autor();
        try {
            String SQL = "select * from autor where id_autor  = " + id_autor + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                autor.setId_autor(id_autor);
                autor.setNombre(rs.getString(2));
                autor.setApellido_paterno(rs.getString(3));
                autor.setApellido_materno(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autor;
    }
}
