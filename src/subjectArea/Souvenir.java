/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectArea;

import java.text.SimpleDateFormat;

public class Souvenir {
    protected String title;
    protected int manufacturerId;
    protected double price;
    protected SimpleDateFormat issueDate;
    
    public Souvenir(String title, int manufacturerId, double price, SimpleDateFormat issueDate){
        this.title = title;
        this.manufacturerId = manufacturerId;
        this.price = price;
        this.issueDate = issueDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the manufacturerId
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the issueDate
     */
    public SimpleDateFormat getIssueDate() {
        return issueDate;
    }
}
