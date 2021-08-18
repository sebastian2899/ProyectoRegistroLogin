package controlador;

import dominio.Persona;
import dominio.PersonaDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "controlador", urlPatterns = {"/controlador"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
           
       
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
     
        int r=0;
        String accion = request.getParameter("accion");
        PersonaDaoImpl persona = new PersonaDaoImpl();
        
        
        if (accion.equals("ingresar")) {
            Persona p = new Persona();
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            
            p.setNombre(nombre);
            p.setEmail(email);
            
            r = persona.validar(p);
            
            if (r==1) {
                request.setAttribute("nombre",nombre);
                request.setAttribute("email",email);
                request.getRequestDispatcher("principal.jsp").forward(request, response);
            }else{
                request.getSession().setAttribute("mensaje","Valores incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
                
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
