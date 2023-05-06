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
public class Orders {
    private int id ;
    private int Quantity;
    private int Product_id;
    private int User_id;
    private String Date;

    public Orders(int id, int Quantity, int Product_id, int User_id, String Date) {
        this.id = id;
        this.Quantity = Quantity;
        this.Product_id = Product_id;
        this.User_id = User_id;
        this.Date = Date;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the Product_id
     */
    public int getProduct_id() {
        return Product_id;
    }

    /**
     * @param Product_id the Product_id to set
     */
    public void setProduct_id(int Product_id) {
        this.Product_id = Product_id;
    }

    /**
     * @return the User_id
     */
    public int getUser_id() {
        return User_id;
    }

    /**
     * @param User_id the User_id to set
     */
    public void setUser_id(int User_id) {
        this.User_id = User_id;
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
