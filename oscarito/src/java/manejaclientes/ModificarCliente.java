package manejaclientes;
import dao.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Controlador;
import modelo.ManejadorDb;
/** * * @author Darka */
public class ModificarCliente extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cliente c=new Cliente();
        c.setCodCliente (request.getParameter (codCliente));
        c.setDni(request.getParameter("dni"));
        c.setNombre(request.getParameter("nombre"));
        c.setApellido1(request.getParameter("apellido1"));
        c.setApellido2(request.getParameter("apellido2"));
        c.setDireccion(request.getParameter("direccion"));
        c.setMunicipio(request.getParameter("municipio"));
        c.setCodProvincia(request.getParameter(codProvincia));
        c.setCodPostal(request.getParameter(codPostal));
        c.setTelefono(request.getParameter("telefono"));
        c.setEmail(request.getParameter("email"));
        c.setEstado(request.getParameter(estado));
        int resultado=0;
        synchronized(ManejadorDb.class){
            resultado=ManejadorDb.actualizarCliente(c);        
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modificación Cliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Actualización de clientes</h1>");
            if(resultado!=Controlador.ERROR_BD){
                out.println("<h3>El cliente se actualizó</h3>");
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
    }
}