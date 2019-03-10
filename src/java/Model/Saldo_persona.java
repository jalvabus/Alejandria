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
public class Saldo_persona {

    private int id_saldo;
    private Persona persona;
    private float saldo;
    private int puntos;

    public Saldo_persona() {
    }

    public Saldo_persona(int id_saldo, Persona persona, float saldo, int puntos) {
        this.id_saldo = id_saldo;
        this.persona = persona;
        this.saldo = saldo;
        this.puntos = puntos;
    }

    public int getId_saldo() {
        return id_saldo;
    }

    public void setId_saldo(int id_saldo) {
        this.id_saldo = id_saldo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
