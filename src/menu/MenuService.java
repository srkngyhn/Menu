package menu;

import database.MenuDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuService {

    // Veritabanından tüm menü öğelerini al
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";

        try (Connection conn = MenuDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int calories = rs.getInt("calories");

                menuItems.add(new ConcreteMenuItem(id, name, type, calories));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // Rastgele menü önerileri al
    public List<MenuItem> getRandomMenuSuggestions(String type, int maxCalories) {
        List<MenuItem> suggestions = new ArrayList<>();

        String sql = "SELECT * FROM menu_items WHERE type = ? AND calories <= ? ORDER BY RAND() LIMIT 4";

        try (Connection conn = MenuDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, type);
            pstmt.setInt(2, maxCalories);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int calories = rs.getInt("calories");

                    suggestions.add(new ConcreteMenuItem(id, name, type, calories));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suggestions;
    }
    public int getCaloriesByNameAndType(String name, String type) {
        String sql = "SELECT calories FROM menu_items WHERE name = ? AND type = ?";
        try (Connection conn = MenuDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, type);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("calories");
                } else {
                    throw new IllegalArgumentException("Menü bulunamadı: " + name + " (" + type + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Veritabanı hatası!");
        }
    }



}