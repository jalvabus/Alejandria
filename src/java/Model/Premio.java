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
public class Premio {

    private int id_premio;
    private String nombre;
    private String categoria;
    private int stock;
    private String descripcion;
    private String imagen;
    private int costo_puntos;

    public Premio() {
    }

    public Premio(int id_premio, String nombre, String categoria, int stock, String descripcion, String imagen, int costo_puntos) {
        this.id_premio = id_premio;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.costo_puntos = costo_puntos;
    }

    public int getId_premio() {
        return id_premio;
    }

    public void setId_premio(int id_premio) {
        this.id_premio = id_premio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCosto_puntos() {
        return costo_puntos;
    }

    public void setCosto_puntos(int costo_puntos) {
        this.costo_puntos = costo_puntos;
    }

}
