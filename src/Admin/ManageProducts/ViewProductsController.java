/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageProducts;

import Entities.Products;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ViewProductsController implements Initializable {
    @FXML
    private TableView<Products> products_table;
    Statement statment;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           String sql = "select * from users";
            ResultSet rs = null;
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ViewProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            ArrayList<Products> pro_list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Products product = new Products(rs.getInt("id"), rs.getInt("Quantity"), rs.getDouble("price"), rs.getString("Name"), rs.getString("Description"), rs.getString("Category"));
                    pro_list.add(product);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            products_table.getItems().setAll(pro_list);
            
        } 
    }    
    
