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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class EditOrderController implements Initializable {

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
        Connection connection = DB.DbConection.get_connection();
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from users";
        ResultSet rs = null;
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Orders> ord_list = new ArrayList<>();
        try {
            while (rs.next()) {
                Orders order = new Orders(rs.getInt("id"), rs.getInt("Product_id"), rs.getInt("User_id"), rs.getInt("Quantity"), rs.getString("Date"));
                ord_list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Orders_table.getItems().setAll(ord_list);

        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        productIdCol1.setCellValueFactory(new PropertyValueFactory("Product_id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory("User_id"));
        quantCol.setCellValueFactory(new PropertyValueFactory("Quantity"));
        dateCol.setCellValueFactory(new PropertyValueFactory("Date"));
        
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productIdCol1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        userIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dateCol.setCellFactory(TextFieldTableCell.<Orders>forTableColumn());
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setId(event.getNewValue());

        });
        productIdCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setProduct_id(event.getNewValue());

        });
        userIdCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setUser_id(event.getNewValue());

        });
        quantCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setQuantity(event.getNewValue());

        });
        dateCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setDate(event.getNewValue());

        });

    }

    @FXML
    private void Edit_Prduct(ActionEvent event) throws SQLException {
        Orders ord = Orders_table.getSelectionModel().getSelectedItem();
        String sql = "UPDATE products SET '" + "', Product_id ='" + ord.getProduct_id()+ "', Users_id='" + ord.getUser_id()
                + "', Quantity ='" + ord.getQuantity()+ "', Date='" + ord.getDate()+ "where  id='" + ord.getId();
        int executeUpdate = statment.executeUpdate(sql);

    }

}
