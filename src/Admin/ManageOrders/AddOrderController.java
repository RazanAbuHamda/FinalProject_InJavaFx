/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageOrders;

import Entities.Products;
import Entities.Users;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author khatib
 */
public class AddOrderController implements Initializable {

    @FXML
    private Pane content;
    @FXML
    private TextField oQuantity;
    @FXML
    private TextField odate;
    @FXML
    private HBox userIdList;
    @FXML
    private ComboBox<Integer> usersList;
    @FXML
    private ComboBox<Integer> productsList;
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

        }
    }

    private boolean validation(String input) {  // ميثود للتحقق من المدخلات انها ليست فارغة
        if (input.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "All fields must be entered", "Please fill in the blank fields", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validate_numbers(String input) {
        if (validation(input)) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Price and Quantity must be number", "Wrong Input", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    @FXML
    private void addOrderButton(ActionEvent event) {
        int user_id = usersList.getSelectionModel().getSelectedItem();
        int product_id = productsList.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(oQuantity.getText());
        String date = odate.getText();
        if (validate_numbers(user_id + "") && validate_numbers(product_id + "") && validate_numbers(quantity + "") && validation(date)) {
            
            try {
                String query = "INSERT INTO orders(Product_id, User_id, Quantity, Date)"
                        + " VALUES('" +product_id + "','" + user_id + "','"
                        + quantity + "','" + date + "')";

                statment.execute(query); // تنفيذ الكويري
                // في حالة جحت الإضافةأ

                JOptionPane.showMessageDialog(null,
                        "Successfully", "Product added", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                // فشل الاضافة
                JOptionPane.showMessageDialog(null,
                        "Failure", "Product Add Failed", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

}
