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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
 import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SearchController implements Initializable {

    @FXML
    ComboBox<String> categCombo;
    @FXML
    private TableColumn<Products, Integer> idCol;
    private TableColumn<Products, String> nameCol;
    @FXML
    private TableColumn<Products, Integer> quantCol;
    private TableColumn<Products, String> categCol;
    private TableColumn<Products, Double> pricCol;
    private TableColumn<Products, String> descCol;
    private TableView<Products> products_table;

    Statement statment;


    @Override
    public void initialize(URL location, ResourceBundle resources)   {
        
           Connection connection = DB.DbConection.get_connection();
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //تعبئة الاصناف
        categCombo.getItems().removeAll(categCombo.getItems());
        categCombo.getItems().addAll("A", "B", "C", "D");
        categCombo.getSelectionModel().select("General");

        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        categCol.setCellValueFactory(new PropertyValueFactory("Category"));
        pricCol.setCellValueFactory(new PropertyValueFactory("Price"));
        descCol.setCellValueFactory(new PropertyValueFactory("Description"));
        quantCol.setCellValueFactory(new PropertyValueFactory("Quantity"));

        ObservableList<Products> pro = FXCollections.observableArrayList();
        String cat = categCombo.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM products WHERE Category='" + cat + "'";
        ResultSet rs = null;
        try {
            rs = statment.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Products> pro_list = new ArrayList<>();
        try {
            while (rs.next()) {
                Products product = new Products(rs.getInt("id"), rs.getInt("Quantity"), rs.getDouble("price"), rs.getString("Name"), rs.getString("Description"), rs.getString("Category"));
                pro_list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

        products_table.getItems().setAll(pro_list);

     }

}
