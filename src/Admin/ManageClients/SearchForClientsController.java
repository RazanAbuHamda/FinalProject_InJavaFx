/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageClients;

import Entities.Users;
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
public class SearchForClientsController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private TableView<Users> clients_table;
    @FXML
    private TableColumn<Users, Integer> idCol;
    @FXML
    private TableColumn<Users, String> nameCol1;
    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private TableColumn<Users, String> mobileCol;
    @FXML
    private TableColumn<Users, String> passwordCol;
    @FXML
    private TableColumn<Users, Integer> roleCol;
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
            Logger.getLogger(SearchForClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //تعبئة    ,,,ids  
//        productIdCombo.getItems().removeAll(productIdCombo.getItems());
//        productIdCombo.getItems().addAll(11, 22, 33, 44);
//        productIdCombo.getSelectionModel().select(11);
        


        
     }

    @FXML
    private void searchButton(ActionEvent event) {
        
        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        nameCol1.setCellValueFactory(new PropertyValueFactory("Product_id"));
        emailCol.setCellValueFactory(new PropertyValueFactory("User_id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory("Quantity"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("Date"));

        ObservableList<Users> pro = FXCollections.observableArrayList();
        int cId = Integer.parseInt(userId.getText());
        String sql = "SELECT * FROM products WHERE Id='" + cId + "'";
        ResultSet rs = null;
   
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SearchForClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ArrayList<Users> cli_list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Users user = new Users(rs.getInt("Id"), rs.getInt("Role"), rs.getString("Name"), rs.getString("Email"),
                            rs.getString("Mobile"), rs.getString("Password"));
                    cli_list.add(user);
                }
        } catch (SQLException ex) {
            Logger.getLogger(SearchForClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clients_table.getItems().setAll(cli_list);

    }

    
}
