/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Connection.DB_Manager;
import Model.Persona;
import Model.Saldo_persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ivan
 */
public class DAO_SaldoPersona {
    DB_Manager db_manager;
    Connection connection;

    public DAO_SaldoPersona() {
        db_manager = new DB_Manager();
        connection = db_manager.getConnection();
    }
    
    public Saldo_persona getSaldoByPersona(int id) {
        Saldo_persona saldo = new Saldo_persona();
        try {
            String SQL = "select * from saldo_persona where id_persona = " + id + ";";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                saldo.setId_saldo(rs.getInt(1));
                saldo.setSaldo(rs.getFloat(3));
                saldo.setPuntos(rs.getInt(4));
                return saldo;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean updateSaldoPuntos(int id, double saldo, int puntos) {
        try {
            String SQL = "UPDATE saldo_persona SET saldo = '"+saldo+"', puntos = '"+puntos+"'  WHERE id_persona = '"+id+"'";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
