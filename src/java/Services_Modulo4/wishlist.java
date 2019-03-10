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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 *
 * @author Juan
 */
@WebServlet(name = "wishlist", urlPatterns = {"/wishlist"})
public class wishlist extends HttpServlet {


    DAO_Wishlist dao_wishlist = new DAO_Wishlist();
    DAO_Usuario dao_usuario = new DAO_Usuario();
    DAO_Libro dao_libro = new DAO_Libro();
    DAO_Compartir_wishlist dao_compartir_wishlist = new DAO_Compartir_wishlist();
    Usuario usuario;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        HttpSession sesion = request.getSession();
        usuario = (Usuario) sesion.getAttribute("user");
        
        if (action.equals("getWishlist")) {
            Wishlist list = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());
            Gson gson = new Gson();
            String wl = gson.toJson(list);
            out.print(wl);
        }
        
        if (action.equals("addToWishlist")) {
            Wishlist list = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());
            Libro libro = dao_libro.getLibroByID(Integer.parseInt(request.getParameter("id_libro")));
            
            if (dao_wishlist.addToWishlist(list, libro)) {
                out.print("success");
            } else {
                out.print("error");
            }
        }
        
        if (action.equals("removeToWishlist")) {
            Wishlist list = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());
            Libro libro = dao_libro.getLibroByID(Integer.parseInt(request.getParameter("id_libro")));

            if (dao_wishlist.removeToWishList(list, libro)) {
                out.print("success");
            } else {
                out.print("error");
            }
        }
        
        if (action.equals("updateFavsWishlist")) {
            Wishlist list = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());
            Libro libro = dao_libro.getLibroByID(Integer.parseInt(request.getParameter("id_libro")));
            libro.setEstado(request.getParameter("favorito"));
            
            if (dao_wishlist.updateFavsWishlist(list, libro)) {
                out.print("success");
            } else {
                out.print("error");
            }
        }
        
        if (action.equals("shareWishlist")) {
            String correo = request.getParameter("correo");
            Usuario usuario_shared = dao_usuario.getOneByCorreo(correo);
            if (usuario_shared == null) {
                out.print("No se ha encontrado ningun usuario con ese correo registrado");
            } else {
                if (usuario_shared.getId_Usuario() == usuario.getId_Usuario()) {
                    out.print("No puedes compartir tu lista de deseos contigo mismo.");
                } else {
                    Wishlist wishlist = dao_wishlist.getWishlistByIDUser(usuario.getId_Usuario());
                    if (dao_wishlist.shareWishlist(wishlist, usuario_shared.getId_Usuario())) {
                        sendEmail(correo);
                        out.print("success");
                    } else {
                        out.print("error");
                    }
                }

            }
        }
        
        
         if (action.equals("getSharedWishlist")) {
            ArrayList<Compartir_wishlist> list = dao_compartir_wishlist.getSharedWishlist(usuario.getId_Usuario());
            Gson gson = new Gson();
            String wl = gson.toJson(list);
            out.print(wl);
        }

         /*
        if (action.equals("SharedWishListByIdUsuario")) {
            List<Wishlist> list = dao_wishlist.getWishlist(Integer.parseInt(request.getParameter("id_usuario")));
            Gson gson = new Gson();
            String wl = gson.toJson(list);
            out.print(wl);
        }
        */
        
    }

    
    public boolean sendEmail(String para) {
        boolean enviado = false;
        try {

            String host = "smtp.gmail.com";

            Properties prop = System.getProperties();
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user", "libreriaalejandria996@gmail.com");
            prop.put("mail.smtp.password", "password123$$");
            prop.put("mail.smtp.port", 587);
            prop.put("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(prop, null);

            MimeMessage message = new MimeMessage(sesion);

            message.setFrom(new InternetAddress("libreriaalejandria996@gmail.com"));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));

            message.setSubject("Lista de Deseos Compartida || Libreria Alejandria");
            String mensaje = "<html>\n"
                    + "    <body>\n"
                    + "        <table class=\"table\">\n"
                    + "            <tbody>\n"
                    + "                <tr>\n"
                    + "                    <td colspan=\"6\"><h2>Mira esta lista de desesos compratida Contigo</h2></td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                    <td>\n"
                    + "                        <div style=\"display: block;\">\n"
                    + "                            <div style=\"border: 1px solid #ccc; text-align: center; transition: all 0.4s ease 0s;background: #fff;\" active\">\n"
                    + "                                <span style=\"background: #3EC1D5 none repeat scroll 0 0;color: #fff;font-size: 13px;font-weight: 700;left: -26px;padding: 2px 25px;position: absolute;text-transform: uppercase;top: 16px;transform: rotate(-45deg);-webkit-transform: rotate(-45deg);-ms-transform: rotate(-45deg);-o-transform: rotate(-45deg);-moz-transform: rotate(-45deg)\">WishList</span>"
                    + "                                <h3 style=\"background: #f5f5f5 none repeat scroll 0 0;color: #333;transition: all 0.4s ease 0s;\"><img src=\"https://png.icons8.com/cotton/50/000000/checklist.png\" width=\"50\"><br/> Wishlist <span></span></h3>\n"
                    + "                                <ol style=\"list-style: outside none none;margin: 0;padding: 0 0 25px;display: block;-webkit-margin-before: 1em;-webkit-margin-after: 1em;-webkit-margin-start: 0px;-webkit-margin-end: 0px;-webkit-padding-start: 40px;\">"
                    + "                                    <li class=\"check\">De: " + usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellido_paterno() + " " + usuario.getPersona().getApellido_materno() + "</li>\n"
                    + "                                    <li class=\"check\"></li>\n"
                    + "                                    <li class=\"check\">Solo tienes que ingresar a nuestro sitio web</li>\n"
                    + "                                </ol>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </td> \n"
                    + "                </tr>\n"
                    + "            </tbody>\n"
                    + "        </table>\n"
                    + "    </body>\n"
                    + "</html>";
            message.setText(mensaje, "utf-8", "html");

            Transport transport = sesion.getTransport("smtp");

            transport.connect(host, "libreriaalejandria996@gmail.com", "password123$$");

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();

            enviado = true;

        } catch (Exception e) {
            System.out.println("Error registro : " + e);
        }

        return enviado;
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
