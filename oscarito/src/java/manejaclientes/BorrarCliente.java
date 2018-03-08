package manejaclientes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controlador;
import modelo.ManejadorDb;

public class BorrarCliente extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni=request.getParameter("dni");
        int resultado=0;
        synchronized(ManejadorDb.class){
            resultado=ManejadorDb.borrarCliente(dni);        
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Borrado de Cliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Borrado de cliente</h1>");
            if(resultado!=Controlador.ERROR_BD){
                out.println("<h3>El cliente se Borró</h3>");
            }else if (resultado==Controlador.NO_USUARIO){
                out.println("<h3>El usuario ya no existe</h3>");
            }else{
                out.println("<h3>Error de base de datos</h3>");
            }
            out.println("<a href='index.html'>Volver</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}