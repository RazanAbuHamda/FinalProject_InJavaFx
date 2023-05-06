/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author hp
 */
public class Invoices {
    private int Id ;
    private int Order_id ;
    private double Total_price;
    private String Date;

    public Invoices(int Id, int Order_id, double Total_price, String Date) {
        this.Id = Id;
        this.Order_id = Order_id;
        this.Total_price = Total_price;
        this.Date = Date;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Order_id
     */
    public int getOrder_id() {
        return Order_id;
    }

    /**
     * @param Order_id the Order_id to set
     */
    public void setOrder_id(int Order_id) {
        this.Order_id = Order_id;
    }

    /**
     * @return the Total_price
     */
    public double getTotal_price() {
        return Total_price;
    }

    /**
     * @param Total_price the Total_price to set
     */
    public void setTotal_price(double Total_price) {
        this.Total_price = Total_price;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
    
    
}
