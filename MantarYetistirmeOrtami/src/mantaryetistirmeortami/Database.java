/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantaryetistirmeortami;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            conn = DriverManager.getConnection(url, user, pass);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String sicaklik, String nem) {
        String sql = "INSERT INTO mantar2(sicaklik,nem) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sicaklik);
            pstmt.setString(2, nem);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<VeriModel> selectAll() {
        ArrayList<VeriModel> vm = new ArrayList<VeriModel>();
        String sql = "SELECT id, sicaklik, nem FROM mantar2";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                vm.add(new VeriModel(rs.getInt("id"), rs.getString("sicaklik"), rs.getString("nem")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vm;
    }

    public int veriSayisi() {
        int veriSayisi = 0;
        String sql = "SELECT COUNT(id) FROM tablo";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                veriSayisi = Integer.parseInt(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return veriSayisi;
    }
//BURAYA BAK

    Boolean girisIzni = false;
    public Boolean girisYap(KullaniciModel km) throws SQLException {
        String sql = "SELECT * FROM user";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                if (km.getUsername().equals(rs.getString("username")) && km.getPassword().equals(rs.getString("password"))) {
                    girisIzni = true;
                }
            }
        } catch (SQLException e) {
            
            System.out.println(e.getMessage());
            girisIzni = false;
        }
        return girisIzni;
    }

}
