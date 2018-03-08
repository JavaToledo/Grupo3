package manejaclientes;
import dao.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ManejadorDb;
/** * * @author Darka */
public class ActualizarCliente extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String dni = request.getParameter("dni");
        List<Cliente> cliente = null;
        String error = "";
        synchronized (ManejadorDb.class) {
            try {
                cliente = ManejadorDb.buscarCliente(dni);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                error = "<h3> No se pudo conectar a la base de datos</h3>";
            }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualización de Cliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Actualización de cliente</h1>");
            if (cliente != null) {
                if (cliente.size() > 0) {
                    
                    out.println("<form action='modificarcliente' method='POST'>");
                    out.println("<p>");
                    out.println("Código de cliente: " + cliente.get(0).getCodCliente());
                    out.println("</p>");
                    out.println("<input type='hidden' name='codCliente' value='"
                            + cliente.get(0).getCodCliente() + "'/>");
                    
                    out.println("<p>");
                    out.println("Dni de cliente: " + cliente.get(0).getDni());
                    out.println("</p>");
                    out.println("<input type='hidden' name='dni' value='"
                            + cliente.get(0).getDni() + "'/>");
                    out.println("<p>");
                    out.println("Nombre del cliente: ");
                    out.println("<input type='text' name='nombre' value='"
                            + cliente.get(0).getNombre() + "'/>");
                    out.println("</p>");
                    out.println("Primer apellido del cliente: ");
                    out.println("<input type='text' name='apellido1' value='"
                            + cliente.get(0).getApellido1() + "'/>");
                    out.println("</p>");
                    out.println("Segundo apellido del cliente: ");
                    out.println("<input type='text' name='apellido2' value='"
                            + cliente.get(0).getApellido2() + "'/>");
                    
                    out.println("<p>");
                    out.println("Dirección del cliente: " + cliente.get(0).getDireccion());
                    out.println("</p>");
                    out.println("<input type='hidden' name='direccion' value='"
                            + cliente.get(0).getDireccion()+ "'/>");
                    
                    out.println("<p>");
                    out.println("Código postal de cliente: " + cliente.get(0).getCodPostal());
                    out.println("</p>");
                    out.println("<input type='hidden' name='codPostal' value='"
                            + cliente.get(0).getCodPostal() + "'/>");
                    
                    out.println("<p>");
                    out.println("Municipio del cliente: " + cliente.get(0).getMunicipio());
                    out.println("</p>");
                    out.println("<input type='hidden' name='municipio' value='"
                            + cliente.get(0).getMunicipio() + "'/>");
                    
                    out.println("<p>");
                    out.println("Código de provincia del cliente: " + cliente.get(0).getCodProvincia());
                    out.println("</p>");
                    out.println("<input type='hidden' name='codProvincia' value='"
                            + cliente.get(0).getCodProvincia()+ "'/>");
                    
                    out.println("</p>");
                    out.println("Teléfono del cliente: ");
                    out.println("<input type='text' name='telefono' value='"
                            + cliente.get(0).getTelefono() + "'/>");
                    
                    out.println("</p>");
                    out.println("Correo electrónico del cliente: ");
                    out.println("<input type='text' name='email' value='"
                            + cliente.get(0).getEmail() + "'/>");
                    
                    out.println("<p>");
                    out.println("Estado del cliente: " + cliente.get(0).getEstado());
                    out.println("</p>");
                    out.println("<input type='hidden' name='estado' value='"
                            + cliente.get(0).getEstado()+ "'/>");
                    
                    out.println("</p>");  
                    out.println("<p>");
                    out.println("<input type='submit' value='Actualizar'/>");
                    out.println("</p>");
                    out.println("</form>");
                } else {
                    out.println("<h3>No hay datos del cliente</h3>");
                }
            } else {
                out.println(error);
            }
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