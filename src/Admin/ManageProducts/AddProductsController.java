/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageProducts;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddProductsController implements Initializable {

    @FXML
    TextField pName;
    @FXML
    TextField pQuantity;
    @FXML
    TextField pPrice;
    @FXML
    ComboBox<String> pCategory;
    @FXML
    TextArea pDescription;
    @FXML
    Button AddProductBtn;
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

    //////////////////////////////////////////////////////////////////////////////////////
    public void addProduct() {
        String name = pName.getText();
        String category = pCategory.getSelectionModel().getSelectedItem();
        double price = Double.parseDouble(pPrice.getText());
        int quantity = Integer.parseInt(pQuantity.getText());
        String descr = pDescription.getText();
        if (validation(name) && validation(category) && validation(descr) && validate_numbers(price + "") && validate_numbers(quantity + "")) {

            try {
                String query = "INSERT INTO products(Name, Category, quantity, price, description)"
                        + " VALUES('" + name + "','" + category + "','" + quantity + "','"
                        + price + "','" + descr + "')";

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
