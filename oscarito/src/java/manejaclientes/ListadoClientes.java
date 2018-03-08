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
public class ListadoClientes extends HttpServlet {
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
        String tablaClientes="";
        List<Cliente> listadoClientes=null;
        synchronized (ManejadorDb.class) {
            try {
                listadoClientes = ManejadorDb.buscarCliente("");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (listadoClientes!=null){
            tablaClientes="<table>"
                    + "<tr>"
                    + "<th> Dni </th>"
                    + "<th> Nombre </th>"
                    + "<th> Primer Apellido </th>"
                    + "<th> Segundo Apellido </th>"
                    + "<th> Teléfono </th>"
                    + "<th> Correo electrónico </th>"
                    + "</tr>";
            for (Cliente c : listadoClientes) {
                tablaClientes+="<tr>"
                        + "<td>"+ c.getDni()+ "</td>"
                        + "<td>"+ c.getNombre()+ "</td>"
                        + "<td>"+ c.getApellido1()+ "</td>"
                        + "<td>"+ c.getApellido2()+ "</td>"
                        + "<td>"+ c.getTelefono()+ "</td>"
                        + "<td>"+ c.getEmail()+ "</td>"
                        + "</tr>";
            }
            tablaClientes+="</table>";
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Clientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Listado de clientes</h1>");
            out.println(tablaClientes);
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
        processRequest(request, response);
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