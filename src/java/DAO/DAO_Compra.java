/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.Compra_wishlist;
import Model.Compra;
import Model.Detalle_compra_wishlist;
import Model.Libro;
import Model.Saldo_persona;
import Model.Tarjeta_Prepago;
import Model.Usuario;
import Model.Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class DAO_Compra {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Compra() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }
    
    /*
      id_compra         | int(30)      | NO   | PRI | NULL    | auto_increment |
    | id_persona        | int(30)      | YES  | MUL | NULL    |                |
    | id_carrito        | int(30)      | YES  | MUL | NULL    |                |
    | fecha_compra      | date         | YES  |     | NULL    |                |
    | total             | decimal(9,2) | YES  |     | NULL    |                |
    | tipo              | text         | YES  |     | NULL    |                |
    | tipo_pago         | text         | YES  |     | NULL    |                |
    | envio             | text         | YES  |     | NULL    |                |
    | puntos_adquiridos | int(30)      | YES  |     | NULL    |                |
+-------------------
    */
    
    public boolean insertCompra(Compra compra){
        try {
            String SQL = "INSERT INTO compra (id_persona, fecha_compra, total, tipo_pago, envio, puntos_adquiridos)  "
                    + "VALUES ('"+compra.getId_persona()+"', null , '"+compra.getTotal()+"', '"+compra.getTipo_pago()+"' , '"+compra.getEnvio()+"' , '"+compra.getPuntos_adquiridos()+"')";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            return ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insertCompraWishList(Usuario usuario, Wishlist wishlist, String folio) {
        try {
            String SQL = "insert into compra_wish_list values (null, " + wishlist.getId_wishlist() + ", '" + folio + "', now(), " + usuario.getId_Usuario() + ");";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            String query = "select MAX(id_compraWL) from compra_wish_list;";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet cdr = id.executeQuery();
            if (cdr.next()) {
                return cdr.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return 0;
        }
    }

    public int getLastfolio() {
        try {
            String query = "select MAX(id_compraWL) from compra_wish_list;";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet cdr = id.executeQuery();
            if (cdr.next()) {
                return cdr.getInt(1);
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return 0;
        }
    }

    public boolean insertDetalleCompraWishList(int id_compra_wish, Libro libro) {
        try {
            String SQL = "insert into detalle_compra_wish_list values (null, " + id_compra_wish + ", '" + libro.getId_libro() + "');";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
            return false;
        }
    }

    public boolean updateTaejetaSaldo(Saldo_persona saldo, Tarjeta_Prepago tarjeta, Libro libro) {
        String SQL = "update tarjeta_prepago set saldo = " + tarjeta.getSaldo() + " where id_tarjeta_prepago = " + tarjeta.getId_tarjeta() + ";";
        System.out.println(SQL);
        String SQL1 = "update saldo_persona set saldo = " + saldo.getSaldo() + ", puntos = " + saldo.getPuntos() + " where id_saldo_persona = " + saldo.getId_saldo() + ";";
        System.out.println(SQL1);

        try {
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();

            PreparedStatement ps1 = connection.prepareCall(SQL1);
            ps1.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return true;
    }

    public Detalle_compra_wishlist getDetalleCompraByIdCompraWish(int id_compra_wishlist) {
        Detalle_compra_wishlist detalle = new Detalle_compra_wishlist();
        try {
            String query = "select * from detalle_compra_wish_list where id_compraWL = " + id_compra_wishlist + ";";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet rs = id.executeQuery();
            if (rs.next()) {
                detalle.setId_detalle_compra_wish(rs.getInt(1));
                DAO_Libro dao_libro = new DAO_Libro();
                detalle.setLibro(dao_libro.getLibroByID(rs.getInt(3)));
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
        }
        return detalle;
    }

    public ArrayList<Compra_wishlist> getCompraWishlistByIdUsuario(Usuario usuario) {
        ArrayList<Compra_wishlist> compra = new ArrayList<>();
        try {
            String query = "select * from compra_wish_list where id_usuario_compra = " + usuario.getId_Usuario() + ";";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet rs = id.executeQuery();
            while (rs.next()) {
                Compra_wishlist detallescompra = new Compra_wishlist();
                detallescompra.setId_compra_wish(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                detallescompra.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                detallescompra.setDetalle_compra(this.getDetalleCompraByIdCompraWish(rs.getInt(1)));
                detallescompra.setFolio(rs.getString(3));
                detallescompra.setFecha(rs.getString(4));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                detallescompra.setUsuario(dao_usuario.getUserByID(rs.getInt(5)));
                compra.add(detallescompra);
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
        }
        return compra;
    }

    public ArrayList<Compra_wishlist> getCompraWishlistByIdUserAndFolio(Usuario usuario, String folio) {
        ArrayList<Compra_wishlist> compra = new ArrayList<>();
        try {
            String query = "select * from compra_wish_list where id_usuario_compra = " + usuario.getId_Usuario() + " and folio = " + folio + ";";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet rs = id.executeQuery();
            while (rs.next()) {
                Compra_wishlist detallescompra = new Compra_wishlist();
                detallescompra.setId_compra_wish(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                detallescompra.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                detallescompra.setDetalle_compra(this.getDetalleCompraByIdCompraWish(rs.getInt(1)));
                detallescompra.setFolio(rs.getString(3));
                detallescompra.setFecha(rs.getString(4));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                detallescompra.setUsuario(dao_usuario.getUserByID(rs.getInt(5)));
                compra.add(detallescompra);
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
        }
        return compra;
    }

    public Compra_wishlist getCompraWishlistById(int id_compra) {
        Compra_wishlist detallescompra = new Compra_wishlist();
        try {
            String query = "select * from compra_wish_list where id_compraWL = " + id_compra + ";";
            PreparedStatement id = connection.prepareCall(query);
            ResultSet rs = id.executeQuery();
            System.out.println(query);
            if (rs.next()) {
                detallescompra.setId_compra_wish(rs.getInt(1));
                DAO_Wishlist dao_wishlist = new DAO_Wishlist();
                detallescompra.setWishlist(dao_wishlist.getWishlistByID(rs.getInt(2)));
                detallescompra.setDetalle_compra(this.getDetalleCompraByIdCompraWish(rs.getInt(1)));
                detallescompra.setFolio(rs.getString(3));
                detallescompra.setFecha(rs.getString(4));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                detallescompra.setUsuario(dao_usuario.getUserByID(rs.getInt(5)));
            }
        } catch (Exception e) {
            System.out.println("Error $Usuarios > login : " + e);
        }
        return detallescompra;
    }
}
