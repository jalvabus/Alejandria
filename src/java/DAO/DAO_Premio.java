/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connection.DB_Manager;
import Model.CanjearPremios;
import Model.Libro;
import Model.Premio;
import Model.Saldo_persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class DAO_Premio {

    DB_Manager db_manager;
    Connection connection;

    public DAO_Premio() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }

    public Premio getPremioByID(int id_premio) {
        Premio premio = new Premio();
        try {
            String SQL = "select * from premios where id_premio = " + id_premio + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                premio.setId_premio(rs.getInt(1));
                premio.setNombre(rs.getString(2));
                premio.setCategoria(rs.getString(3));
                premio.setStock(rs.getInt(4));
                premio.setDescripcion(rs.getString(5));
                premio.setImagen(rs.getString(6));
                premio.setCosto_puntos(rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return premio;
    }

    public ArrayList<Premio> getAll() {
        ArrayList<Premio> premios = new ArrayList<>();
        try {
            String SQL = "select * from premios;";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Premio premio = new Premio();
                premio.setId_premio(rs.getInt(1));
                premio.setNombre(rs.getString(2));
                premio.setCategoria(rs.getString(3));
                premio.setStock(rs.getInt(4));
                premio.setDescripcion(rs.getString(5));
                premio.setImagen(rs.getString(6));
                premio.setCosto_puntos(rs.getInt(7));
                premios.add(premio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return premios;
    }

    public ArrayList<CanjearPremios> getPremiosByIdPersona(int id_persona) {
        ArrayList<CanjearPremios> premios = new ArrayList<>();
        try {
            String SQL = "select * from canjear_premio where id_persona = " + id_persona + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CanjearPremios premioCanje = new CanjearPremios();
                premioCanje.setId_canjear_premios(rs.getInt(1));
                premioCanje.setPremio(this.getPremioByID(rs.getInt(2)));
                DAO_Usuario dao_usuario = new DAO_Usuario();
                premioCanje.setPersona(dao_usuario.getPersonaByID(rs.getInt(3)));
                premioCanje.setFecha(rs.getString(4));
                premioCanje.setCantidad(rs.getInt(5));
                premios.add(premioCanje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return premios;
    }

    public boolean updatePuntosPersona(Saldo_persona saldo) {
        try {
            String SQL = "update saldo_persona set saldo = " + saldo.getSaldo() + ", puntos = " + saldo.getPuntos() + " where id_saldo_persona = " + saldo.getId_saldo() + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStockPremio(Premio premio) {
        try {
            String SQL = "update premios set stok = " + premio.getStock() + " where id_premio = " + premio.getId_premio() + ";";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertCanjear_premio(Premio premio, int id_persona, int cantidad) {
        try {
            String SQL = "insert into canjear_premio values (null," + premio.getId_premio() + ", " + id_persona + ", now(), " + cantidad + ");";
            PreparedStatement ps = connection.prepareCall(SQL);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
