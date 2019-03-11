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
public class CanjearPremios {

    private int id_canjear_premios;
    private Premio premio;
    private Persona persona;
    private String fecha;
    private int cantidad;

    public CanjearPremios() {
    }

    public CanjearPremios(int id_canjear_premios, Premio premio, Persona persona, String fecha, int cantidad) {
        this.id_canjear_premios = id_canjear_premios;
        this.premio = premio;
        this.persona = persona;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getId_canjear_premios() {
        return id_canjear_premios;
    }

    public void setId_canjear_premios(int id_canjear_premios) {
        this.id_canjear_premios = id_canjear_premios;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
