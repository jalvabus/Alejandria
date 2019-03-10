/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class DAO_Libro {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Libro() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }

    public ArrayList<Libro> getAll() {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        try {
            String SQL = "select * from libro;";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId_libro(rs.getInt(1));
                libro.setNombre(rs.getString(2));
                libro.setIsbn(rs.getString(3));
                libro.setEditorial(rs.getString(4));

                DAO_Autor dao_autor = new DAO_Autor();
                libro.setAutor(dao_autor.getAutorByID(rs.getInt(5)));

                libro.setDescripcion(rs.getString(6));
                libro.setCosto(rs.getFloat(7));
                libro.setFoto(rs.getString(8));
                libro.setStock(rs.getString(9));
                libro.setCategoria(rs.getString(10));
                libro.setEstado(rs.getString(11));
                libros.add(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    public Libro getLibroByID(int id_libro) {
        Libro libro = new Libro();
        try {
            String SQL = "select * from libro where id_libro  = " + id_libro + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                libro.setId_libro(id_libro);
                libro.setNombre(rs.getString(2));
                libro.setIsbn(rs.getString(3));
                libro.setEditorial(rs.getString(4));

                DAO_Autor dao_autor = new DAO_Autor();
                libro.setAutor(dao_autor.getAutorByID(rs.getInt(5)));

                libro.setDescripcion(rs.getString(6));
                libro.setCosto(rs.getFloat(7));
                libro.setFoto(rs.getString(8));
                libro.setStock(rs.getString(9));
                libro.setCategoria(rs.getString(10));
                libro.setEstado(rs.getString(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }
}
