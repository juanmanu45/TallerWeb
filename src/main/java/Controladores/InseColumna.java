/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.ServiciosEsquema;
import DAO.ServiciosTabla;
import VO.Esquema;
import VO.Tabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
public class InseColumna extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rq = request.getRequestDispatcher("inserTabla.jsp");
            ServiciosTabla ser = new ServiciosTabla();
            ArrayList<Tabla> lis = null;
            lis = (ArrayList<Tabla>) ser.listarTablas();
            if (lis.size() > 0) {

                request.setAttribute("lis", lis);
            } else {
                request.setAttribute("lis", null);
            }
            rq.forward(request, response);

        } catch (URISyntaxException ex) {
            Logger.getLogger(InseColumna.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean resultado = false;
            
            ServiciosTabla ser = new ServiciosTabla();
            
            String nombre = request.getParameter("name");
            String id = request.getParameter("idTabla");
            String nombreES = request.getParameter("nombreEsquema");
            
            int id_tabla = Integer.parseInt(id);
            
            if (id.trim().length() > 0 && nombre.trim().length() > 0 && nombreES.trim().length() > 0) {
                
                resultado = true;
                
                Tabla r = new Tabla();
                
                
                ServiciosTabla st = new ServiciosTabla();
                
                r = ser.extraerTabla(nombre);
                Tabla t = new Tabla(id_tabla, r.getId_esquema(), nombre);
                st.agragarTabla(t);
                
                RequestDispatcher rq = request.getRequestDispatcher("inserTabla.jsp");
                
                if (resultado == true) {
                    request.setAttribute("resultado", true);
                } else {
                    request.setAttribute("resultado", false);
                }
                
                rq.forward(request, response);
            } else {
                request.setAttribute("resultado", false);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(InseColumna.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
