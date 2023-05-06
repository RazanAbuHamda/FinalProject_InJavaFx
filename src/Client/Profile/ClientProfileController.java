/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import LogIn.LogInController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class ClientProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clientViewProfileButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientViewProfile.fxml"));
            try {
                Parent parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void clientEditProfileButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientEditProfile.fxml"));
            try {
                Parent parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void clientBackButton(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientProfile.fxml"));
            try {
                Parent parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
}
