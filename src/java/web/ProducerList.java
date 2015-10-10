package web;

import db.DatabaseConnector;
import db.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import subjectArea.Manufacturer;
import subjectArea.Souvenir;

public class ProducerList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseConnector dbConnector = new DatabaseConnector();
        DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
        ResultSet res = dbManager.getManufacturers();
        response.setContentType("text/html;charset=UTF-8");
            
            List producerList = new ArrayList<>();
            SimpleDateFormat tempDate  = new SimpleDateFormat();
            while(res.next()){
                producerList.add(new Manufacturer(res.getString("title"),res.getString("country")));
            }
            request.setAttribute("size", producerList.size());
            request.setAttribute("producerArrayList", producerList);
            
        request.getRequestDispatcher("producerList.jsp").forward(request, response);
        dbConnector.closeConnection();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            
            List souvenirList = new ArrayList<>();
            SimpleDateFormat tempDate  = new SimpleDateFormat();
            while(res.next()){
                tempDate.format(res.getDate("date"));
                souvenirList.add(new Souvenir(res.getString("title"),res.getInt("manufacturer_id"),res.getDouble("price"),tempDate));
            }
            request.setAttribute("name",choosenManufacturerName);
            request.setAttribute("size", souvenirList.size());
            request.setAttribute("souvenirArrayList", souvenirList);
            request.getRequestDispatcher("souvenirList.jsp").forward(request, response);
            
            dbConnector.closeConnection();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
        try {
            DatabaseConnector dbConnector;
            dbConnector = new DatabaseConnector();
            DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
            if(!"".equals(request.getParameter("ProducerName")) && request.getParameter("ProducerName") != null  && request.getParameter("ProducerCountry") != "" && request.getParameter("ProducerCountry") != null){
                dbManager.insertManufacturer(new Manufacturer(request.getParameter("ProducerName"),request.getParameter("ProducerCountry")));
                dbConnector.closeConnection();
                processRequest(request, response);
            }
            else if(request.getParameter("deleteName")!= null){
                dbManager.deleteManufacturer(request.getParameter("deleteName"));
                dbConnector.closeConnection();
                processRequest(request, response);
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProducerList.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex.getMessage());
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
