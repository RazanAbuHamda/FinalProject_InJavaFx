/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ManageOrders;

import Entities.Orders;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
public class DeleteOrderController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private TableView<Orders> Orders_table;
    @FXML
    private TableColumn<Orders, Integer> idCol;
    @FXML
    private TableColumn<Orders, Integer> productIdCol1;
    @FXML
    private TableColumn<Orders, Integer> userIdCol;
    @FXML
    private TableColumn<Orders, Integer> quantCol;
    @FXML
    private TableColumn<Orders, String> dateCol;
    Statement statment;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
            Connection connection = DB.DbConection.get_connection();
            try {
                statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // get prod from table
            
            String sql = "select * from users";
            ResultSet rs = statment.executeQuery(sql);
            
            ArrayList<Orders> ord_list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Orders order = new Orders(rs.getInt("id"),rs.getInt("Product_id"),rs.getInt("User_id"), rs.getInt("Quantity"), rs.getString("Date"));
                    ord_list.add(order);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DeleteOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Orders_table.getItems().setAll(ord_list);
            
        } catch (SQLException ex) {
            Logger.getLogger(DeleteOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Delete_Prduct(ActionEvent event) throws SQLException {
        Orders pro = Orders_table.getSelectionModel().getSelectedItem();
        String sql = "delete from users where id=" + pro.getId();
        int executeUpdate = statment.executeUpdate(sql);

    }

}

