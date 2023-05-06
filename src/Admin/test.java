/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class test extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();

//        loader.setController(new AddProductsController());
        Parent root = loader.load(new FileInputStream(new File("C:\\Users\\hp\\Documents\\NetBeansProjects\\FinalProject_InJavaFx\\src\\Admin\\AddProduct.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Screen");
        stage.show();
        
 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
