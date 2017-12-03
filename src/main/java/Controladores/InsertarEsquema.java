/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.ServiciosEsquema;
import VO.Esquema;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Manuel
 */
public class InsertarEsquema extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        boolean resultado = false;
        String respuesta = null;
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        int id_esquema = Integer.parseInt(id);

        if (id.trim().length() > 0 && nombre.trim().length() > 0) {
            try {
                resultado = true;
                Esquema es = new Esquema(id_esquema, nombre);
                ServiciosEsquema ser = new ServiciosEsquema();
                ser.agregarEsquema(es);
                
                RequestDispatcher rq = request.getRequestDispatcher("InsertarEsquema.jsp");
                
                if (resultado == true) {
                    request.setAttribute("resultado", true);
                } else {
                    request.setAttribute("resultado", false);
                }
                
                rq.forward(request, response);
            } catch (URISyntaxException ex) {
                Logger.getLogger(InsertarEsquema.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("resultado", false);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
