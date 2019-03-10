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
public class Detalle_compra_wishlist {

    private int id_detalle_compra_wish;

    public Detalle_compra_wishlist(int id_detalle_compra_wish, Libro libro) {
        this.id_detalle_compra_wish = id_detalle_compra_wish;
        this.libro = libro;
    }
    private Libro libro;

    public Detalle_compra_wishlist() {
    }

    public int getId_detalle_compra_wish() {
        return id_detalle_compra_wish;
    }

    public void setId_detalle_compra_wish(int id_detalle_compra_wish) {
        this.id_detalle_compra_wish = id_detalle_compra_wish;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

}
