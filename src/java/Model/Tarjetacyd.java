/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Ivan| id_tarjetaCyD  | int(30)                  | NO   | PRI | NULL    | auto_increment |
| id_banco       | int(30)                  | YES  | MUL | NULL    |                |
| numero_tarjeta | int(30)                  | YES  |     | NULL    |                |
| CCV            | int(5)                   | YES  |     | NULL    |                |
| vigencia       | date                     | YES  |     | NULL    |                |
| saldo          | decimal(9,2)             | YES  |     | NULL    |                |
| tipo_tarjetal  | enum('credito','debito') | YES  |     | NULL    |                |
| numero
 */
public class Tarjetacyd {
    private int id_tarjetacyd;
    private int id_banco;
    //sin usar
    private int numero_tarjeta;
    private int cvv;
    private String vigencia;
    private double saldo;
    private String tipo_tarjetal;
    private String numero;

    public int getId_tarjetacyd() {
        return id_tarjetacyd;
    }

    public void setId_tarjetacyd(int id_tarjetacyd) {
        this.id_tarjetacyd = id_tarjetacyd;
    }
    
    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public int getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(int numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo_tarjetal() {
        return tipo_tarjetal;
    }

    public void setTipo_tarjetal(String tipo_tarjetal) {
        this.tipo_tarjetal = tipo_tarjetal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    
}
