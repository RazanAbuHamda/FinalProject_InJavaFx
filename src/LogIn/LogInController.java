/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogIn;

import DB.DbConection;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sun.security.util.Password;


/**
 * FXML Controller class
 *
 * @author khatib
 */
public class LogInController implements Initializable {

    private Label emailErr;
    private Label PassErr;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField emailTextField;
    private DbConection dbConnection;
    private Statement statement;
//    private String loginInfo;
//    private String[] infoArray;
    @FXML
    private BorderPane mainPanel;
    @FXML
    private Label errMsg;
    @FXML
    private Label passErr;
    static public int idStatic;

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
    private void toRegisterScreenButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerpage.fxml"));
        try {
            Parent parent = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logInbutton(ActionEvent event) throws SQLException {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String query = "SELECT * FROM users WHERE email = '" + email + "' and password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.wasNull()) {
            errMsg.setText("User does not exit or please check the data entered.");
        } else {
            String roleQuery = "SELECT role FROM users WHERE email = '" + email + "' and password = '" + password + "'";
            idStatic  = Integer.parseInt("SELECT id FROM users WHERE email = '" + email + "' and password = '" + password + "'");
    if(roleQuery

    
        
            == "client"){
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("clientProfile.fxml"));
            try {
                Parent parent = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    else if(roleQuery

    
        == "admin"){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminProfile.fxml"));
        try {
            Parent parent = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
}

    private boolean validateInput(String input) {
        if (!input.equals("")) {
            return true;
        } else {
            errMsg.setText("You must enter values");
            return false;
        }

    }

    private boolean validate_password(String pass) {
        if (validateInput(pass)) {
            if (passwordTextField.getText().length() < 7) {
                PassErr.setText("Password must be at min 7");
                return false;
            } else {
                PassErr.setText("");
                return true;
            }

        }
        return false;
    }

    private boolean validate_numaric(String input) {
        if (validateInput(input)) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public void successDialoge() {
        Dialog<String> done = new Dialog<>();
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        done.getDialogPane().getButtonTypes().add(ok);
        done.getDialogPane().getStylesheets().add("file:src/javafxlab_ass2_jobapply/styl.css");
        done.setTitle("success");
        done.setContentText("Successfully LogIn.");
        done.show();
    }

    public void faildDialoge() {
        Dialog<String> done = new Dialog<>();
        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        done.getDialogPane().getButtonTypes().add(ok);
        done.getDialogPane().getStylesheets().add("file:src/javafxlab_ass2_jobapply/styl.css");
        done.setTitle("success");
        done.setContentText("Faild to LogIn.");
        done.show();
    }
}
