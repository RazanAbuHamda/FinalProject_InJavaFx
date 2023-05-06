/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Register;

import DB.DbConection;
import LogIn.LogInController;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.security.util.Password;


/**
 * FXML Controller class
 *
 * @author khatib
 */
public class RegisterationController implements Initializable {

    @FXML
    private TextField nameRegisterTextField;
    @FXML
    private TextField emailRegisterTextField;
    @FXML
    private PasswordField passwordRegisterTextField;
     private Statement statement ;
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
    private void logInAfterRegisterToDashboardButton(ActionEvent event) {
        String name = nameRegisterTextField.getText();
        String email = emailRegisterTextField.getText();
        String pass = passwordRegisterTextField.getText();
        
        String addQuery = "inseret into users values ("+name+","+email+","+pass+"'"+"client)";
         FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
            try {
                Parent parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void backToLogInScreenButton(ActionEvent event) {
    }



}
