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
public class Compra_wishlist {

    private int id_compra_wish;
    private Wishlist wishlist;
    private String folio;
    private String fecha;
    private Usuario usuario;
    private Detalle_compra_wishlist detalle_compra;

    public Compra_wishlist() {
    }

    
    public Compra_wishlist(int id_compra_wish, Wishlist wishlist, String folio, String fecha, Usuario usuario, Detalle_compra_wishlist detalle_compra) {
        this.id_compra_wish = id_compra_wish;
        this.wishlist = wishlist;
        this.folio = folio;
        this.fecha = fecha;
        this.usuario = usuario;
        this.detalle_compra = detalle_compra;
    }
    
    public int getId_compra_wish() {
        return id_compra_wish;
    }

    public void setId_compra_wish(int id_compra_wish) {
        this.id_compra_wish = id_compra_wish;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Detalle_compra_wishlist getDetalle_compra() {
        return detalle_compra;
    }

    public void setDetalle_compra(Detalle_compra_wishlist detalle_compra) {
        this.detalle_compra = detalle_compra;
    }
    
    

}
