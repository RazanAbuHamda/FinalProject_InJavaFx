/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author khatib
 */


public class EditClientProfileController implements Initializable {

    @FXML
    private TextField editName;
    @FXML
    private TextField editEmail;
    @FXML
    private TextField editMobile;
    private Statement statement;

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
        String userInfo = "SELECT Name , Email ,Mobile from users where Id="+idStatic;
         try {
            ResultSet rs = statement.executeQuery(userInfo);
            editName.setText(rs.getString("Name"));
            editEmail.setText(rs.getString("Email"));
            editMobile.setText(rs.getString("Mobile"));
            
            String editN = editName.getText();
            String editE = editEmail.getText();
            String editM = editMobile.getText();
            
            String updateClientInfo = "UPDATE users set Name = "+editN+" ,Email ="+editE+" ,Mobile ="+editM;
            int executeUpdate = statement.executeUpdate(updateClientInfo);
             System.out.println("affected rows: " +executeUpdate);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewClientProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
