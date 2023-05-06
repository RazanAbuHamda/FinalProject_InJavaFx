/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.ManageProducts;

import Entities.Products;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditproductController implements Initializable {

    @FXML
    private Button editProductBtn;
    @FXML
    private TableColumn<Products, Integer> idCol;
    @FXML
    private TableColumn<Products, String> nameCol;
    @FXML
    private TableColumn<Products, Integer> quantCol;
    @FXML
    private TableColumn<Products, String> categCol;
    @FXML
    private TableColumn<Products, Double> pricCol;
    @FXML
    private TableColumn<Products, String> descCol;
    @FXML
    private TableView<Products> products_table;
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
            Logger.getLogger(EditproductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from users";
        ResultSet rs = null;
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EditproductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Products> pro_list = new ArrayList<>();
        try {
            while (rs.next()) {
                Products product = new Products(rs.getInt("id"), rs.getInt("Quantity"), rs.getDouble("price"), rs.getString("Name"), rs.getString("Description"), rs.getString("Category"));
                pro_list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        products_table.getItems().setAll(pro_list);

        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        categCol.setCellValueFactory(new PropertyValueFactory("Category"));
        pricCol.setCellValueFactory(new PropertyValueFactory("Price"));
        descCol.setCellValueFactory(new PropertyValueFactory("Description"));
        quantCol.setCellValueFactory(new PropertyValueFactory("Quantity"));
        nameCol.setCellFactory(TextFieldTableCell.<Products>forTableColumn());
        categCol.setCellFactory(TextFieldTableCell.<Products>forTableColumn());
        descCol.setCellFactory(TextFieldTableCell.<Products>forTableColumn());
        pricCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        quantCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nameCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setName(event.getNewValue());

        });
        categCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setCategory(event.getNewValue());

        });
        descCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setDescription(event.getNewValue());

        });
        pricCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setPrice(event.getNewValue());

        });
        quantCol.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setQuantity(event.getNewValue());

        });

    }

    @FXML
    private void Edit_Prduct(ActionEvent event) throws SQLException {
        Products pro = products_table.getSelectionModel().getSelectedItem();
        String sql = "UPDATE products SET name='" + pro.getName() + "', category='" + pro.getCategory() + "', quantity='" + pro.getQuantity()
                + "', price='" + pro.getPrice() + "', description='" + pro.getDescription() + "where  id='" + pro.getId();
        int executeUpdate = statment.executeUpdate(sql);

    }

}
