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

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
            Wishlist wishlist = dao_wishlist.getWishlistByIDUser(Integer.parseInt(request.getParameter("id_wish_list")));

            saldo.setSaldo(saldo.getSaldo() - Float.parseFloat(request.getParameter("total")));
            saldo.setPuntos(saldo.getPuntos() + Integer.parseInt(request.getParameter("puntos")));

            tarjeta.setSaldo(tarjeta.getSaldo() - Float.parseFloat(request.getParameter("total")));

            String folio = "1000" + dao_compra.getLastfolio();

            int id_compra_wishlist = dao_compra.insertCompraWishList(usuario, wishlist, folio);
            dao_compra.insertDetalleCompraWishList(id_compra_wishlist, libro);

            dao_compra.updateTaejetaSaldo(saldo, tarjeta, libro);

            generarComprobante(dao_compra.getCompraWishlistById(id_compra_wishlist));
        }

    }

    private void generarComprobante(Compra_wishlist compra) {
        String ruta = "C:\\reportes\\";

        try {
            Document documento = new Document();

            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta + "Comprobante" + compra.getFolio() + ".pdf"));

            documento.open();

            //Este es el nombre de la cabecera
            documento.add(new Paragraph("\nCOMPROBANTE DE COMPRA " + compra.getFolio(), FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.WHITE)));

            String IMAGE = "C:/reportes/favicon.png";

            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = Image.getInstance(IMAGE);
            image.setAbsolutePosition(500, 750);
            image.scaleAbsoluteHeight(50);
            image.scaleAbsoluteWidth(50);
            canvas.saveState();
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.6f);
            canvas.setGState(state);
            canvas.addImage(image);
            canvas.restoreState();

            PdfPTable tablita;
            tablita = new PdfPTable(1);

            tablita.setWidthPercentage(100);
            tablita.setSpacingBefore(20f); //Space before table
            tablita.setSpacingAfter(10f); //Space after table

            PdfPCell celdat1 = new PdfPCell(new Paragraph("COMPROBANTE DE COMPRA " + compra.getFolio(), FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK)));

            celdat1.setBorderColor(BaseColor.WHITE);
            celdat1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdat1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            tablita.addCell(celdat1);

            documento.add(tablita);

            PdfPTable tabla;
            tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f); //Space before table
            tabla.setSpacingAfter(10f); //Space after table

            PdfPCell celda1 = new PdfPCell(new Paragraph("Libro", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Autor", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda3 = new PdfPCell(new Paragraph("Editorial", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda4 = new PdfPCell(new Paragraph("Costo", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);

            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);

            tabla.addCell(new PdfPCell(new Paragraph(compra.getDetalle_compra().getLibro().getNombre(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph(compra.getDetalle_compra().getLibro().getAutor().getNombre(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph(compra.getDetalle_compra().getLibro().getEditorial(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph("$ " + String.valueOf(compra.getDetalle_compra().getLibro().getCosto()), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            // Adding Table to document        
            documento.add(tabla);

            double costoLibro = compra.getDetalle_compra().getLibro().getCosto();

            documento.add(new Paragraph("DATOS DEL RECEPTOR: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Nombre: " + compra.getUsuario().getPersona().getNombre() + " " + compra.getUsuario().getPersona().getApellido_paterno() + " " + compra.getUsuario().getPersona().getApellido_materno(), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Direccion: " + compra.getUsuario().getPersona().getDireccion(), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("\nDETALLES: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Costo de envio: $ 150", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Iva(16%): " + String.valueOf(costoLibro * 0.16), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Total: " + String.valueOf((costoLibro * 0.16) + 150 + costoLibro), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            // Closing the document       
            documento.close();

            mostrarReporte(ruta + "Comprobante" + compra.getFolio() + ".pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarReporte(String ruta) {
        try {
            File abrir = new File(ruta);
            Desktop.getDesktop().open(abrir);
        } catch (IOException e) {
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
