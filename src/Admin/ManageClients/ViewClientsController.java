/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageClients;

import Entities.Users;
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
public class ViewClientsController implements Initializable {

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
            Logger.getLogger(ViewClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Users> cli_list = new ArrayList<>();
        try {
            while (rs.next()) {
                Users user = new Users(rs.getInt("Id"), rs.getInt("Role"), rs.getString("Name"), rs.getString("Email"),
                        rs.getString("Mobile"), rs.getString("Password"));
                cli_list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clients_table.getItems().setAll(cli_list);

    }
}
