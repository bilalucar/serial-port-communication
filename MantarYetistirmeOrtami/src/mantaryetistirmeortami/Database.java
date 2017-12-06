/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantaryetistirmeortami;

import java.sql.*;

/**
 *
 * @author Bilal
 */
public class Database {

 public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/mantar.db";
            String user = "mantar";
            String pass = "mantar";
            // create a connection to the database
            conn = DriverManager.getConnection(url,user,pass);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return conn;
    }
 
public void insert(VeriModel vm) {
        String sql = "INSERT INTO mantar2(sicaklik,nem) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vm.getSicakik());
            pstmt.setString(2, vm.getNem());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
  
}

