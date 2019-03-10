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
public class Compartir_wishlist {
    private int id_compartir;
    private Wishlist wishlist;
    private Usuario usuario;
    private String alias;

    public Compartir_wishlist() {
    }

    public Compartir_wishlist(int id_compartir, Wishlist wishlist, Usuario usuario, String alias) {
        this.id_compartir = id_compartir;
        this.wishlist = wishlist;
        this.usuario = usuario;
        this.alias = alias;
    }

    public int getId_compartir() {
        return id_compartir;
    }

    public void setId_compartir(int id_compartir) {
        this.id_compartir = id_compartir;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
}
