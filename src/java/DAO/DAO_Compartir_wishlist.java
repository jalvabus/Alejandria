/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.Compartir_wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class DAO_Compartir_wishlist {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Compartir_wishlist() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }

    public ArrayList<Compartir_wishlist> getSharedWishlist(int id_usuario) {
        ArrayList<Compartir_wishlist> wish_list = new ArrayList<Compartir_wishlist>();
        try {
            String SQL = "select * from compartir_wish_list where id_usuario_compartido = " + id_usuario + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            System.out.println(SQL);
            while (rs.next()) {
                Compartir_wishlist lista = new Compartir_wishlist();
                lista.setId_compartir(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                lista.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                lista.setUsuario(dao_usuario.getUserByID(rs.getInt(3)));
                lista.setAlias(rs.getString(4));
                wish_list.add(lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wish_list;
    }

    public Compartir_wishlist getSharedWishlistByID(int id_compartirWL) {
        Compartir_wishlist lista = new Compartir_wishlist();
        try {
            String SQL = "select * from compartir_wish_list where id_compartirWL = " + id_compartirWL + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            System.out.println(SQL);
            if (rs.next()) {

                lista.setId_compartir(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                lista.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                lista.setUsuario(dao_usuario.getUserByID(rs.getInt(3)));
                lista.setAlias(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean updateAliasWishList(Compartir_wishlist wish) {
        try {
            String SQL = "update compartir_wish_list set alias = '" + wish.getAlias() + "' where id_compartirWL = " + wish.getId_compartir() + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Compartir_wishlist> getSharedWishlistAndAlias(int id_usuario, String alias) {
        ArrayList<Compartir_wishlist> wish_list = new ArrayList<Compartir_wishlist>();
        try {
            String SQL = "select * from compartir_wish_list where id_usuario_compartido = " + id_usuario + " and alias like '%" + alias + "%';";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            System.out.println(SQL);
            while (rs.next()) {
                Compartir_wishlist lista = new Compartir_wishlist();
                lista.setId_compartir(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                lista.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                lista.setUsuario(dao_usuario.getUserByID(rs.getInt(3)));
                lista.setAlias(rs.getString(4));
                wish_list.add(lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wish_list;
    }
}
