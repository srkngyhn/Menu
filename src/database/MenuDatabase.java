package database;

import java.sql.*;

public class MenuDatabase {
    static String url = "jdbc:mysql://localhost:3306/menuDaBa";
    static String username = "root"; // Kullanıcı adınızı yazın
    static String password = "password"; // MySQL şifrenizi yazın

    // Veritabanı bağlantısını oluştur
    public static Connection connect() {
        try {
            return DriverManager.getConnection(url, username, password); // Kullanıcı adı ve şifreyi ekleyin
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tabloları başlatmak için kullanılabilir (örnek kod)
    @SuppressWarnings("unused")
	public static void initDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            if (conn != null) { // Bağlantının başarılı olup olmadığını kontrol edin
                String sql = "CREATE TABLE IF NOT EXISTS menu_items (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "type VARCHAR(50)," +
                        "calories INT" +
                        ");";
                stmt.execute(sql);
                System.out.println("Tablo başarıyla oluşturuldu veya zaten mevcut.");
            } else {
                System.out.println("Veritabanı bağlantısı başarısız.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}