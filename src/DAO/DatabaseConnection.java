package DAO;
import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
	private static Connection conn;
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Dotenv dotenv = Dotenv.load();
				String username = dotenv.get("DB_USERNAME");
                String password = dotenv.get("DB_PASSWORD");
                
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            String url = "jdbc:sqlserver://localhost:1433;databaseName=KhoHang;encrypt=true;trustServerCertificate=true";

	            conn = DriverManager.getConnection(url, username, password);
	        } catch (ClassNotFoundException e) {
	            System.out.println("Không tìm thấy driver: " + e.getMessage());
	            e.printStackTrace();
	        } catch (SQLException e) {
	        	System.out.println("Lỗi kết nối SQL: " + e.getMessage());
	            e.printStackTrace();
	        }
		}
		return conn;
	}

}
