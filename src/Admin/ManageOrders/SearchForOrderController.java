/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageOrders;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class SearchForOrderController implements Initializable {

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
//    private ComboBox<Integer> productIdCombo;
    Statement statment;

    @FXML
    private TextField userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Connection connection = DB.DbConection.get_connection();
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(SearchForOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //تعبئة    ,,,ids  
//        productIdCombo.getItems().removeAll(productIdCombo.getItems());
//        productIdCombo.getItems().addAll(11, 22, 33, 44);
//        productIdCombo.getSelectionModel().select(11);
        


        
     }

    @FXML
    private void searchButton(ActionEvent event) {
        
        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        productIdCol1.setCellValueFactory(new PropertyValueFactory("Product_id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory("User_id"));
        quantCol.setCellValueFactory(new PropertyValueFactory("Quantity"));
        dateCol.setCellValueFactory(new PropertyValueFactory("Date"));

        ObservableList<Orders> pro = FXCollections.observableArrayList();
        int oId = Integer.parseInt(userId.getText());
        String sql = "SELECT * FROM products WHERE User_id='" + oId + "'";
        ResultSet rs = null;
   
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SearchForOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ArrayList<Orders> pro_list = new ArrayList<>();
        try {
            while (rs.next()) {
                    Orders order = new Orders(rs.getInt("id"),rs.getInt("Product_id"),rs.getInt("User_id"), rs.getInt("Quantity"), rs.getString("Date"));
                pro_list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchForOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Orders_table.getItems().setAll(pro_list);

    }

    
}
