/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services_Modulo4;

import DAO.*;
import Model.*;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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
@WebServlet(name = "premios", urlPatterns = {"/premios"})
public class premios extends HttpServlet {

    Usuario usuario;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        HttpSession sesion = request.getSession();
        usuario = (Usuario) sesion.getAttribute("user");
        
        DAO_Premio dao_premio = new DAO_Premio();
        DAO_Usuario dao_usuario = new DAO_Usuario();

        if (action.equals("getAll")) {

            Gson gson = new Gson();
            String premios = gson.toJson(dao_premio.getAll());
            out.println(premios);
        }

        if (action.equals("getPremiosByUser")) {
            Gson gson = new Gson();
            String premios = gson.toJson(dao_premio.getPremiosByIdPersona(usuario.getPersona().getIdPersona()));
            out.println(premios);
        }

        if (action.equals("canjearPremio")) {
            Saldo_persona saldo = dao_usuario.getSaldoByIdPersona(usuario.getPersona().getIdPersona());
            Premio premio = dao_premio.getPremioByID(Integer.parseInt(request.getParameter("id_premio")));

            saldo.setPuntos(saldo.getPuntos() - (premio.getCosto_puntos() * Integer.parseInt(request.getParameter("cantidad"))));
            premio.setStock(premio.getStock() - Integer.parseInt(request.getParameter("cantidad")));

            dao_premio.updatePuntosPersona(saldo);
            dao_premio.updateStockPremio(premio);
            dao_premio.insertCanjear_premio(premio, usuario.getPersona().getIdPersona(), Integer.parseInt(request.getParameter("cantidad")));

            generarComprobante(premio, Integer.parseInt(request.getParameter("cantidad")));
            
            Gson gson = new Gson();
            String premios = gson.toJson(dao_premio.getAll());
            out.println(premios);
        }
    }

    private void generarComprobante(Premio premio, int cantidad) {
        String ruta = "C:\\reportes\\";

        try {
            Document documento = new Document();

            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta + "ComprobantePremio.pdf"));

            documento.open();

            //Este es el nombre de la cabecera
            documento.add(new Paragraph("\nCOMPROBANTE DE CANJE ", FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.WHITE)));

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

            PdfPCell celdat1 = new PdfPCell(new Paragraph("COMPROBANTE DE CANJE ", FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK)));

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

            PdfPCell celda1 = new PdfPCell(new Paragraph("Premio", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda2 = new PdfPCell(new Paragraph("Categoria", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda3 = new PdfPCell(new Paragraph("Descripcion", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            PdfPCell celda4 = new PdfPCell(new Paragraph("Cantidad", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

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

            tabla.addCell(new PdfPCell(new Paragraph(premio.getNombre(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph(premio.getCategoria(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph(premio.getDescripcion(), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(cantidad), FontFactory.getFont("arial", 10, Font.NORMAL, BaseColor.BLACK))));
            // Adding Table to document        
            documento.add(tabla);

            documento.add(new Paragraph("DATOS DEL RECEPTOR: ", FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Nombre: " + usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellido_paterno() + " " + usuario.getPersona().getApellido_materno(), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));
            documento.add(new Paragraph("Direccion: " + usuario.getPersona().getDireccion(), FontFactory.getFont("arial", 10, Font.BOLD, BaseColor.BLACK)));

            // Closing the document       
            documento.close();

            mostrarReporte(ruta + "ComprobantePremio.pdf");
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
