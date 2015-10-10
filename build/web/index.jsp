<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.DatabaseManager"%>
<%@page import="db.DatabaseConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-theme.css">
        <title>Souvenirs</title>
    </head>
    <body>
        <h1 class="alert alert-info" align="center">
            Welcome to Souvenirs
        </h1>
            <a href="producer"><input type="button" value="View Producers"  /></a>
            <a href="producer/add"><input type="button" value="Add producer" /></a>
            <a href="souvenir"><input type="button" value="View Souvenirs" /></a>
            <a href="souvenir/add"><input type="button" value="Add Souvenir" /></a>
            <form action="souvenir">
                <select name="producerName" class="selectpicker btn-primary" data-live-search=”true”>
                    <option value="any" selected>any</option>
                    <%  DatabaseConnector dbConnector;
                        dbConnector = new DatabaseConnector();
                        DatabaseManager dbManager = new DatabaseManager(dbConnector.getConnection());
                        ResultSet res = dbManager.getManufacturers();
                        while(res.next()){
                            out.println("<option value = \""+ res.getString("title") +"\">"+ res.getString("title") +"</option>");
                        }
                    %>
                </select>
                <input type="submit" value="Find by name" />
            </form>
            <form action="souvenir">
                <select name="producerCountry" class="selectpicker btn-primary">
                    <option value="any" selected>any</option>
                    <%  res = dbManager.getManufacturers();
                        while(res.next()){
                            out.println("<option value = \""+ res.getString("country") +"\">"+ res.getString("country") +"</option>");
                        }
                        dbConnector.closeConnection();
                    %>
                </select>
                <input type="submit" value="Find by country" />
            </form>
    </body>
</html>
