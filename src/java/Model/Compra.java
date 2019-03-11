/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Ivan
 */
public class Compra {
    
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
    
    private int id_compra;
    private int id_persona;
    private int id_carrito;
    private String fecha_compra;
    private double total;
    private String tipo;
    private String tipo_pago;
    private String envio;
    private int puntos_adquiridos;

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public int getPuntos_adquiridos() {
        return puntos_adquiridos;
    }

    public void setPuntos_adquiridos(int puntos_adquiridos) {
        this.puntos_adquiridos = puntos_adquiridos;
    }
    
    
    
}
