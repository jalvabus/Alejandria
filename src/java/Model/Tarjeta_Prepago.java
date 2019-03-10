/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Juan
 */
public class Tarjeta_Prepago {

    private int id_tarjeta;
    private int numero;
    private String estado;
    private float saldo;
    private Persona persona;

    public Tarjeta_Prepago() {
    }

    public Tarjeta_Prepago(int id_tarjeta, int numero, String estado, float saldo, Persona persona) {
        this.id_tarjeta = id_tarjeta;
        this.numero = numero;
        this.estado = estado;
        this.saldo = saldo;
        this.persona = persona;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
