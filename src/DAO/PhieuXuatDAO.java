package DAO;

import Model.tbl_PhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatDAO {
    private Connection conn;

    public PhieuXuatDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public tbl_PhieuXuat getPhieuXuatById(int id) {
        tbl_PhieuXuat phieuXuat = null;

        try {
            String sql = "SELECT * FROM PhieuXuat WHERE MaPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                phieuXuat = new tbl_PhieuXuat();
                phieuXuat.setMaPhieuXuat(resultSet.getInt("MaPhieuXuat"));
                phieuXuat.setNgayXuat(resultSet.getDate("NgayXuat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return phieuXuat;
    }

    public void addPhieuXuat(tbl_PhieuXuat phieuXuat) {
        try {
            String sql = "INSERT INTO PhieuXuat (NgayXuat) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, phieuXuat.getNgayXuat());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePhieuXuat(tbl_PhieuXuat phieuXuat) {
        try {
            String sql = "UPDATE PhieuXuat SET NgayXuat = ? WHERE MaPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, phieuXuat.getNgayXuat());
            preparedStatement.setInt(2, phieuXuat.getMaPhieuXuat());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePhieuXuat(int id) {
        try {
            String sql = "DELETE FROM PhieuXuat WHERE MaPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<tbl_PhieuXuat> getDanhSachPhieuXuat() {
        List<tbl_PhieuXuat> danhSachPhieuXuat = new ArrayList<>();

        try {
            String sql = "SELECT * FROM PhieuXuat";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tbl_PhieuXuat phieuXuat = new tbl_PhieuXuat();
                phieuXuat.setMaPhieuXuat(resultSet.getInt("MaPhieuXuat"));
                phieuXuat.setNgayXuat(resultSet.getDate("NgayXuat"));

                danhSachPhieuXuat.add(phieuXuat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachPhieuXuat;
    }
}