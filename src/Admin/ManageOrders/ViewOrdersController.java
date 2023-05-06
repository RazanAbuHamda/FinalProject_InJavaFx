/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageOrders;

import Admin.ManageProducts.ViewProductsController;
import Entities.Orders;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class ViewOrdersController implements Initializable {
 @FXML
    private TableView<Orders> orders_table;
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
            
            ArrayList<Orders> ord_list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Orders order = new Orders(rs.getInt("id"),rs.getInt("Product_id"),rs.getInt("User_id"), rs.getInt("Quantity"), rs.getString("Date"));
                    ord_list.add(order);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
            orders_table.getItems().setAll(ord_list);
            
        
    }    
    
}
