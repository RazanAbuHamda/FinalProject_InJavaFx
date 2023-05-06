/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageClients;

import Entities.Products;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class DeleteClientsController implements Initializable {

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
    private Button DeleteClienttBtn;

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
                Logger.getLogger(DeleteClientsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            // get prod from table
            String sql = "select * from users";
            ResultSet rs = statment.executeQuery(sql);

            ArrayList<Users> cli_list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Users user = new Users(rs.getInt("Id"), rs.getInt("Role"), rs.getString("Name"), rs.getString("Email"),
                            rs.getString("Mobile"), rs.getString("Password"));
                    cli_list.add(user);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DeleteClientsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            clients_table.getItems().setAll(cli_list);

        } catch (SQLException ex) {
            Logger.getLogger(DeleteClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void deleteClientBtn(ActionEvent event) throws SQLException {
        Users user = clients_table.getSelectionModel().getSelectedItem();
        String sql = "delete from users where id=" + user.getId();
        int executeUpdate = statment.executeUpdate(sql);

    }

}
