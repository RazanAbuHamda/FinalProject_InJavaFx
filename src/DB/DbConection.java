/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class DbConection {
    
      public static Connection get_connection(){
         Connection connection=null;
         try {
            String url="jdbc:mysql://localhost:127.0.0.1/orderapp?serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url,"root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConection.class.getName()).log(Level.SEVERE, null, ex);
        }
//         System.out.println("connected");
        return connection;
    }
      
     
    
}
