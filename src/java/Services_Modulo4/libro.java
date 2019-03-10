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
import java.util.ArrayList;
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
@WebServlet(name = "libro", urlPatterns = {"/libro"})
public class libro extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        DAO_Libro $libros = new DAO_Libro();
        DAO_Compra dao_compra = new DAO_Compra();
        
        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("user");
        
        Gson gson = new Gson();

        if (action.equalsIgnoreCase("getLibros")) {
            String libros = gson.toJson($libros.getAll());
            out.println(libros);
        }
        
        if (action.equalsIgnoreCase("getLibrosComprados")) {
            ArrayList<Compra_wishlist> compras = dao_compra.getCompraWishlistByIdUsuario(usuario);
            String wl = gson.toJson(compras);
            out.print(wl);
        }
        
        if (action.equalsIgnoreCase("getLibrosCompradosByFolio")) {
            ArrayList<Compra_wishlist> compras = dao_compra.getCompraWishlistByIdUserAndFolio(usuario, request.getParameter("folio"));
            String wl = gson.toJson(compras);
            out.print(wl);
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
