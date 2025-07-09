package test;
import DAO.DatabaseConnection;
import java.sql.Connection;

public class DBCNtest {
	public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
}
