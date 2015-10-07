package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.DatabaseConnector;
import db.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import subjectArea.Manufacturer;
import subjectArea.Souvenir;

/**
 *
 * @author Dmitry
 */
public class SouvenirList extends HttpServlet {

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
            out.println("<title>Servlet SouvenirList</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.css\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-theme.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class=\"alert alert-info\" align=\"center\">Servlet SouvenirList at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("producerName");
        String country = request.getParameter("producerCountry");
        if("any".equals(country) && "any".equals(name)){
            processRequest(request, response);
        }
        else{
            DatabaseConnector dbConnector;
            DatabaseManager dbManager = null;
            try {
            dbConnector = new DatabaseConnector();
                dbManager = new DatabaseManager(dbConnector.getConnection());
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SouvenirList.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet res = null;
            if("any".equals(name) || null == name){
                try {
                    res = dbManager.getSouvenirsByCountry(country);
                } catch (SQLException ex) {
                    Logger.getLogger(SouvenirList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if("any".equals(country)|| null == country){
                try {
                    res = dbManager.getSouvenirsByManufacturers(name);
                } catch (SQLException ex) {
                    Logger.getLogger(SouvenirList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                //TODO both paranetrs
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SouvenirList</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.css\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-theme.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class=\"alert alert-info\" align=\"center\">SouvenirList at " + request.getContextPath() + "</h1>");
            out.println("<table border=\"1\">");
                out.println("<tbody>");
                    try {
                        while(res.next()){
                            out.println("<tr>");
                                out.println("<td>");
                                    out.println(res.getString("id"));
                                out.println("</td>");
                                out.println("<td>");
                                    out.println(res.getString("title"));
                                out.println("</td>");
                                out.println("<td>");
                                    out.println(res.getString("price"));
                                out.println("</td>");
                                out.println("<td>");
                                    out.println(res.getString("date"));
                                out.println("</td>");
                            out.println("</tr>");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SouvenirList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
           /*DatabaseConnector dbConnector;
        try {
            dbConnector = new DatabaseConnector();
            DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
            int ProducerID = Integer.parseInt(request.getParameter("Producer_ID"));
            double price = Double.parseDouble(request.getParameter("Price"));
            dbManager.insertSouvenir(new Souvenir(request.getParameter("Name"),ProducerID,price,request.getParameter("Date")));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            PrintWriter out = response.getWriter();
            out.println(request.getParameter("Name"));
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
