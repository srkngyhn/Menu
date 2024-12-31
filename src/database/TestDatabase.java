package database;

import java.sql.*;

public class TestDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/menuDaBa";
        String user = "root";
        String password = "password"; // MySQL şifrenizi buraya yazın

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Bağlantı başarılı!");

            String query = "SELECT * FROM menu_items";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + 
                                       ", Name: " + rs.getString("name") + 
                                       ", Type: " + rs.getString("type") + 
                                       ", Calories: " + rs.getInt("calories"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
