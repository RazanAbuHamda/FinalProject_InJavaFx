/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ChangePassword;

import DB.DbConection;
import LogIn.LogInController;
import static LogIn.LogInController.idStatic;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



/**
 * FXML Controller class
 *
 * @author khatib
 */
public class ChangePasswordController implements Initializable {

      @FXML
    private PasswordField oldPassTextField;
    @FXML
    private PasswordField newPassFirstTextField;
    @FXML
    private PasswordField newPassSeconedTextField;
    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            Connection connection = DbConection.get_connection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void changePasswordButton(ActionEvent event) {
        String userInfo = "SELECT Password from users where Id="+idStatic;
        try {
            ResultSet rs = statement.executeQuery(userInfo);
            String passFromDB = rs.getString("Password");
            String newPass1 = newPassFirstTextField.getText();
            String newPass2 = newPassSeconedTextField.getText();
            if(newPass1.equals(newPass2)){
                String updateClientPassword = "UPDATE users set Password="+newPass1;
            int executeUpdate = statement.executeUpdate(updateClientPassword);
             System.out.println("affected rows: " +executeUpdate);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
