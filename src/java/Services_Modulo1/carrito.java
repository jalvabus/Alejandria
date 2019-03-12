

package Services_Modulo1;

import DAO.DAO_Compra;
import DAO.DAO_Libro;
import DAO.DAO_SaldoPersona;
import DAO.DAO_Tarjetacyd;
import Model.Carrito;
import Model.Compra;
import Model.Libro;
import Model.Saldo_persona;
import Model.Tarjetacyd;
import Model.Usuario;
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
    DAO_SaldoPersona daoSaldo = new DAO_SaldoPersona();
    DAO_Compra daoCompra = new DAO_Compra();
    DAO_Libro daoLibro = new DAO_Libro();
    DAO_Tarjetacyd daoTarjeta = new DAO_Tarjetacyd();
    
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
            
            if(carrito.getTotal() < 600){
                carrito.setTotal(carrito.getTotal() + 50);
            }
            
            session.setAttribute("carrito", carrito);
            out.print("success");
        }
        
        if(action.equals("removeLibroInCarrito")){
            int index = Integer.parseInt(request.getParameter("index"));
            carrito.getLibros().remove(index);
            
            carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
            carrito.setIva(carrito.getSubtotal() * 0.16);
            carrito.setTotal(carrito.getSubtotal() + carrito.getIva());
            
            if(carrito.getTotal() < 600){
                carrito.setTotal(carrito.getTotal() + 50);
            }
            
            session.setAttribute("carrito", carrito);
            out.print("success");
        }
        
        if(action.equals("dropCarrito")){
            carrito.getLibros().clear();
            
            carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
            carrito.setIva(carrito.getSubtotal() * 0.16);
            carrito.setTotal(carrito.getSubtotal() + carrito.getIva());
            
            session.setAttribute("carrito", carrito);
            out.print("success");
        }
        
        if(action.equals("comprarBySaldo")){
            
            String dirEnvio = request.getParameter("dir_envio");
            Usuario user = (Usuario) session.getAttribute("user");
            carrito = (Carrito)session.getAttribute("carrito");
            
            Saldo_persona saldoPersona = daoSaldo.getSaldoByPersona(user.getPersona().getIdPersona());
            System.out.println("saldo " + saldoPersona.getSaldo());
            System.out.println("total " + carrito.getTotal());

            if(saldoPersona == null){
                out.print("null");
            }else if(saldoPersona.getSaldo() < carrito.getTotal()){
                out.print("menor");
            }else{
                int puntosAd = (int) Math.round((  carrito.getTotal() / 10));
                daoSaldo.updateSaldoPuntos(user.getPersona().getIdPersona(), saldoPersona.getSaldo() - carrito.getTotal(), saldoPersona.getPuntos() + puntosAd);
                Compra compra = new Compra();
                compra.setId_persona(user.getPersona().getIdPersona());
                
                compra.setTotal(carrito.getTotal());
                compra.setTipo_pago("SALDO ELECTRONICO");
                compra.setEnvio(dirEnvio);
                compra.setPuntos_adquiridos( puntosAd);
                
                daoCompra.insertCompra(compra);
                
                for(Libro libro: carrito.getLibros()){
                    daoLibro.updateStokLibro(libro.getId_libro());
                }
                
                carrito.getLibros().clear();
            
                carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
                carrito.setIva(carrito.getSubtotal() * 0.16);
                carrito.setTotal(carrito.getSubtotal() + carrito.getIva());

                session.setAttribute("carrito", carrito);
                
                out.print("succes");
            }
        }
        
        if(action.equals("comprarByTarjeta")){
            
            String dirEnvio = request.getParameter("dir_envio");
            String numero = request.getParameter("numero");
            String cvv = request.getParameter("cvv");
            Usuario user = (Usuario) session.getAttribute("user");
            carrito = (Carrito)session.getAttribute("carrito");
            
            Tarjetacyd tarjeta = daoTarjeta.getTarjeta(numero, cvv, "");
            //System.out.println("saldo tarjeta" + tarjeta.getSaldo());
            //System.out.println("total carrito" + carrito.getTotal());

            if(tarjeta == null){
                out.print("null");
            }else if(tarjeta.getSaldo() < carrito.getTotal()){
                out.print("menor");
            }else{

                daoTarjeta.updateSaldoTarjeta(numero, tarjeta.getSaldo() - carrito.getTotal());
                Compra compra = new Compra();
                compra.setId_persona(user.getPersona().getIdPersona());
                compra.setTotal(carrito.getTotal());
                compra.setTipo_pago(tarjeta.getTipo_tarjetal());
                compra.setEnvio(dirEnvio);
                compra.setPuntos_adquiridos(0);
                
                daoCompra.insertCompra(compra);
                
                for(Libro libro: carrito.getLibros()){
                    daoLibro.updateStokLibro(libro.getId_libro());
                }
                
                carrito.getLibros().clear();
            
                carrito.setSubtotal(calculaSubTotal(carrito.getLibros()));
                carrito.setIva(carrito.getSubtotal() * 0.16);
                carrito.setTotal(carrito.getSubtotal() + carrito.getIva());

                session.setAttribute("carrito", carrito);
                
                out.print("succes");
            }
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
