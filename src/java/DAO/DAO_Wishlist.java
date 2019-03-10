/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.*;
import java.sql.*;
import java.util.ArrayList;
import DAO.*;

/**
 *
 * @author Juan
 */
public class DAO_Wishlist {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Wishlist() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }
    
    public boolean addToWishlist(Wishlist wish) {
        final String SQL = "INSERT INTO wishlist VALUES(null,'" + wish.getUsuario().getId_Usuario() + "', , (SELECT DATE_ADD(now(), INTERVAL 30 DAY)));";
        System.out.println(SQL);
        try {
            CallableStatement cs = connection.prepareCall(SQL);
            cs.execute();
        } catch (Exception e) {
            System.err.print("Error al añadir a la lista de deseos");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<DetalleWishlist> getDetalleWishList(int id_wishlist) {
        ArrayList<DetalleWishlist> detalles = new ArrayList<>();
        try {
            String SQL = "select * from detalle_wish where id_wish_list  = " + id_wishlist;
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleWishlist detalle = new DetalleWishlist();
                detalle.setId_detalle_wishlist(rs.getInt(1));
                detalle.setId_wishlist(id_wishlist);
                DAO_Libro dao_libro = new DAO_Libro();
                detalle.setLibro(dao_libro.getLibroByID(rs.getInt(3)));
                detalle.setFavorito(rs.getBoolean(4));
                detalles.add(detalle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public Wishlist getWishlistByIDUser(int id_usuario) {
        Wishlist wl = new Wishlist();
        try {
            String SQL = "select * from wish_list where id_usuario  = " + id_usuario + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            System.out.println(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wl.setId_wishlist(rs.getInt(1));

                DAO_Usuario dao_usuario = new DAO_Usuario();
                wl.setUsuario(dao_usuario.getUserByID(rs.getInt(2)));
                wl.setLibros(this.getDetalleWishList(rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wl;
    }

     public Wishlist getWishlistByID(int id_wish_list) {
        Wishlist wl = new Wishlist();
        try {
            String SQL = "select * from wish_list where id_wish_list  = " + id_wish_list + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            System.out.println(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wl.setId_wishlist(rs.getInt(1));

                DAO_Usuario dao_usuario = new DAO_Usuario();
                wl.setUsuario(dao_usuario.getUserByID(rs.getInt(2)));
                wl.setLibros(this.getDetalleWishList(rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wl;
    }
     
    public boolean addToWishlist(Wishlist wl, Libro libro) {
        final String SQL = "INSERT INTO detalle_wish VALUES(null,'" + wl.getId_wishlist() + "','" + libro.getId_libro() + "', false)";
        System.out.println(SQL);
        try {
            CallableStatement cs = connection.prepareCall(SQL);
            cs.execute();
        } catch (Exception e) {
            System.err.print("Error al añadir a la lista de deseos");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateFavsWishlist(Wishlist wl, Libro libro) {
        final String SQL = "update detalle_wish set favorito = " + libro.getEstado() + " where id_libro = " + libro.getId_libro() + " and id_wish_list = " + wl.getId_wishlist() + " ;";
        System.out.println(SQL);
        try {
            CallableStatement cs = connection.prepareCall(SQL);
            cs.execute();
        } catch (Exception e) {
            System.err.print("Error al añadir a la lista de deseos");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeToWishList(Wishlist wl, Libro libro) {
        final String SQL = "delete from detalle_wish where id_wish_list = '" + wl.getId_wishlist() + "' and id_libro = '" + libro.getId_libro() + "';";
        System.out.println(SQL);
        try {
            CallableStatement cs = connection.prepareCall(SQL);
            cs.execute();
        } catch (Exception e) {
            System.err.print("Error al remover a la lista de deseos " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean shareWishlist(Wishlist wishlist, int id_usuario_shared) {

        final String SQL = "INSERT INTO compartir_wish_list VALUES(null,'" + wishlist.getId_wishlist() + "', '" + id_usuario_shared + "', '" + id_usuario_shared + "')";
        System.out.println(SQL);
        try {
            CallableStatement cs = connection.prepareCall(SQL);
            cs.execute();
        } catch (Exception e) {
            System.err.print("Error al compartir la lista de deseos");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
}
