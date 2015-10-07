package web;

import db.DatabaseConnector;
import db.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import subjectArea.Manufacturer;

public class ProducerList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseConnector dbConnector = new DatabaseConnector();
        DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
        ResultSet res = dbManager.getManufacturers();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Servlet ProducerList</title>");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.css\">");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-theme.css\">");
                out.println("</head>");
                out.println("<body>");
                    out.println("<h1 class=\"alert alert-info\" align=\"center\">Servlet ProducerList at " + request.getContextPath() + "</h1>");
                    out.println("<table border=\"1\">");
                        out.println("<tbody>");
                            while(res.next()){
                                out.println("<tr>");
                                    out.println("<td>");
                                        out.println(res.getString("id"));
                                    out.println("</td>");
                                    out.println("<td>");
                                        out.print("<a href=\"souvenir?producerName="+res.getString("title")+"\">");
                                            out.print(res.getString("title"));
                                        out.println("</a>");
                                    out.println("</td>");
                                    out.println("<td>");
                                        out.println(res.getString("country"));
                                    out.println("</td>");
                                out.println("</tr>");
                            }
                        out.println("</tbody>");
                    out.println("</table>");
                out.println("</body>");
            out.println("</html>");
        }
        dbConnector.closeConnection();
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
        try {
            PrintWriter out = response.getWriter();
            String choosenManufacturerName = request.getParameter("name");
            if(choosenManufacturerName == null || "".equals(choosenManufacturerName)){
                processRequest(request, response);
            }
            DatabaseConnector dbConnector;
            dbConnector = new DatabaseConnector();
            DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
            ResultSet res = dbManager.getSouvenirsByManufacturers(choosenManufacturerName);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Servlet ProducerList</title>");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap.css\">");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"bootstrap-theme.css\">");
                out.println("</head>");
                out.println("<body>");
                    out.println("<h1 class=\"alert alert-info\" align=\"center\">Product list of " + request.getParameter("name") + "</h1>");
                    out.println("<table border=\"1\">");
                        out.println("<tbody>");
                            while(res.next()){
                                out.println("<tr>");
                                    out.println("<td>");
                                        out.println(res.getString("title"));
                                    out.println("</td>");
                                    out.println("<td>");
                                        out.print(res.getString("price"));
                                    out.println("</td>");
                                    out.println("<td>");
                                        out.println(res.getString("date"));
                                    out.println("</td>");
                                out.println("</tr>");
                            }
                        out.println("</tbody>");
                    out.println("</table>");
                out.println("</body>");
            out.println("</html>");
            dbConnector.closeConnection();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
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
            DatabaseConnector dbConnector;
        try {
            dbConnector = new DatabaseConnector();
            DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
            dbManager.insertManufacturer(new Manufacturer(request.getParameter("ProducerName"),request.getParameter("ProducerCountry")));
            dbConnector.closeConnection();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
        }
            PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
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
