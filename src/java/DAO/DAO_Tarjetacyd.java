/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Connection.DB_ManagerBanco;
import Model.Tarjetacyd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ivan
 */
public class DAO_Tarjetacyd {
    DB_ManagerBanco db_manager;
    Connection connection;

    public DAO_Tarjetacyd() {
        db_manager = new DB_ManagerBanco();
        connection = db_manager.getConnection();
    }
    
    public Tarjetacyd getTarjeta(String numero, String cvv, String vigencia){
        Tarjetacyd tarjeta = new Tarjetacyd();
        try {
            String SQL = "select * from tarjetacyd where numero = '" + numero + "' and ccv = '"+cvv+"';";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tarjeta.setId_tarjetacyd(rs.getInt(1));
                tarjeta.setId_banco(rs.getInt(2));
                tarjeta.setNumero_tarjeta(rs.getInt(3));
                tarjeta.setCvv(rs.getInt(4));
                tarjeta.setVigencia(rs.getString(5));
                tarjeta.setSaldo(rs.getDouble(6));
                tarjeta.setTipo_tarjetal(rs.getString(7));
                tarjeta.setNumero(rs.getString(8));
                return tarjeta;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean updateSaldoTarjeta(String numero, double saldo ){
        try {
            String SQL = "UPDATE tarjetacyd SET saldo = '"+saldo+"' WHERE numero = '"+numero+"'";
            System.out.println(SQL);
            PreparedStatement ps = connection.prepareCall(SQL);
            return ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
