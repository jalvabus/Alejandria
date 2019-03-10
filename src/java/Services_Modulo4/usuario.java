/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services_Modulo4;

import DAO.*;
import Model.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
@WebServlet(name = "usuario", urlPatterns = {"/usuario"})
public class usuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        
        DAO_Usuario dao_usuario = new DAO_Usuario();
        DAO_Libro dao_libro = new DAO_Libro();
        DAO_Wishlist dao_wishlist = new DAO_Wishlist();
        DAO_Compra dao_compra = new DAO_Compra();
        
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("user");

        if (action.equals("getDatosTarjeta")) {
            Tarjeta_Prepago list = dao_usuario.getDatosTarjetaByIDPersona(usuario.getPersona().getIdPersona(), request.getParameter("numero"));
            Gson gson = new Gson();
            String wl = gson.toJson(list);
            out.print(wl);
        }

        if (action.equals("getPuntos")) {
            Saldo_persona saldo = dao_usuario.getSaldoByIdPersona(usuario.getPersona().getIdPersona());
            Gson gson = new Gson();
            String wl = gson.toJson(saldo);
            out.print(wl);
        }

        if (action.equals("comprarLibro")) {
            Libro libro = dao_libro.getLibroByID(Integer.parseInt(request.getParameter("id_libro")));
            Saldo_persona saldo = dao_usuario.getSaldoByIdPersona(usuario.getPersona().getIdPersona());
            Tarjeta_Prepago tarjeta = dao_usuario.getDatosTarjetaByIDPersona(usuario.getPersona().getIdPersona(), request.getParameter("numero"));
            Wishlist wishlist = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());

            saldo.setSaldo(saldo.getSaldo() - Float.parseFloat(request.getParameter("total")));
            saldo.setPuntos(saldo.getPuntos() + Integer.parseInt(request.getParameter("puntos")));

            tarjeta.setSaldo(tarjeta.getSaldo() - Float.parseFloat(request.getParameter("total")));
            
            String folio = "1000" + dao_compra.getLastfolio();
            
            int id_compra_wishlist = dao_compra.insertCompraWishList(usuario, wishlist, folio);
            dao_compra.insertDetalleCompraWishList(id_compra_wishlist, libro);
            
            dao_compra.updateTaejetaSaldo(saldo, tarjeta, libro);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
