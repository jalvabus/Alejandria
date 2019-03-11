

package Services_Modulo1;

import Model.Carrito;
import Model.Libro;
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

@WebServlet(name = "carrito", urlPatterns = {"/carrito"})
public class carrito extends HttpServlet {

    HttpSession session;
    Carrito carrito = new Carrito();
    private ArrayList<Libro> libros;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        session = request.getSession();
        
        String action = request.getParameter("action");
        carrito = (Carrito)session.getAttribute("carrito");
        if(carrito == null){
            carrito = new Carrito();
        }
        
        if(action.equals("getCarrito")){
            Gson gson = new Gson();
            String wl = gson.toJson(carrito);
            System.out.print(wl);
            out.print(wl);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        session = request.getSession();
        
        String action = request.getParameter("action");
        
        carrito = (Carrito)session.getAttribute("carrito");
        if(carrito == null){
            carrito = new Carrito();
        }
        
        if(action.equals("addLibroInCarrito")){
            Libro libro = new Libro();
            libro.setId_libro(Integer.parseInt(request.getParameter("id_libro")));
            libro.setNombre(request.getParameter("nombre"));
            libro.setCosto(Float.parseFloat(request.getParameter("costo")));
            System.out.println(libro.getCosto());
            carrito.getLibros().add(libro);
            
            carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
            carrito.setIva(carrito.getSubtotal() * 0.16);
            carrito.setTotal(carrito.getSubtotal() + carrito.getIva());
            
            session.setAttribute("carrito", carrito);
            out.print("success");
        }
        
        if(action.equals("removeLibroInCarrito")){
            int index = Integer.parseInt(request.getParameter("index"));
            carrito.getLibros().remove(index);
            
            carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
            carrito.setIva(carrito.getSubtotal() * 0.16);
            carrito.setTotal(carrito.getSubtotal() + carrito.getIva());
            
            session.setAttribute("carrito", carrito);
            out.print("success");
        }
    }
    
    public double calculaSubTotal(ArrayList<Libro> libros) {
        double subtotal = 0;
        for(Libro libro: libros){
            subtotal += libro.getCosto();
        }
        return subtotal;
    }

}
