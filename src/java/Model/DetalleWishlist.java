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
public class DetalleWishlist {

    private int id_detalle_wishlist;
    private int id_wishlist;
    private Libro libro;
    private boolean favorito;

    public DetalleWishlist() {
    }

    public DetalleWishlist(int id_detalle_wishlist, int id_wishlist, Libro libro, boolean favorito) {
        this.id_detalle_wishlist = id_detalle_wishlist;
        this.id_wishlist = id_wishlist;
        this.libro = libro;
        this.favorito = favorito;
    }

    public int getId_detalle_wishlist() {
        return id_detalle_wishlist;
    }

    public void setId_detalle_wishlist(int id_detalle_wishlist) {
        this.id_detalle_wishlist = id_detalle_wishlist;
    }

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}
