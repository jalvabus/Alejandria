/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services_Modulo4;

import Model.Compra_wishlist;
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

/**
 *
 * @author Juan
 */
@WebServlet(name = "reporte", urlPatterns = {"/reporte"})
public class reporte extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet reporte</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reporte at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        generarComprobante(new Compra_wishlist());
        processRequest(request, response);
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
        processRequest(request, response);
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
