/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Juan
 */
public class Wishlist {
    private int id_wishlist;
    private Usuario usuario;
    private Date vigencia;
    private ArrayList<DetalleWishlist> libros;
    
    public Wishlist() {
    }

    public Wishlist(int id_wishlist, Usuario usuario, Date vigencia, ArrayList<DetalleWishlist> libros) {
        this.id_wishlist = id_wishlist;
        this.usuario = usuario;
        this.vigencia = vigencia;
        this.libros = libros;
    }

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public ArrayList<DetalleWishlist> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<DetalleWishlist> libros) {
        this.libros = libros;
    }


    
    
}
