package DAO;

import Model.tbl_ChiTietPhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuXuatDAO {
    private Connection conn;

    public ChiTietPhieuXuatDAO() {
        this.conn = DatabaseConnection.getConnection(); // Sử dụng DatabaseConnection
    }

    public tbl_ChiTietPhieuXuat getChiTietPhieuXuatById(int id) {
        tbl_ChiTietPhieuXuat chiTietPhieuXuat = null;

        try {
            String sql = "SELECT * FROM ChiTietPhieuXuat WHERE MaChiTietPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chiTietPhieuXuat = new tbl_ChiTietPhieuXuat();
                chiTietPhieuXuat.setMaChiTietPhieuXuat(resultSet.getInt("MaChiTietPhieuXuat"));
                chiTietPhieuXuat.setMaPhieuXuat(resultSet.getInt("MaPhieuXuat"));
                chiTietPhieuXuat.setMaSanPham(resultSet.getInt("MaSanPham"));
                chiTietPhieuXuat.setSoLuong(resultSet.getInt("SoLuong"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chiTietPhieuXuat;
    }

    public void addChiTietPhieuXuat(tbl_ChiTietPhieuXuat chiTietPhieuXuat) {
        try {
            String sql = "INSERT INTO ChiTietPhieuXuat (MaPhieuXuat, MaSanPham, SoLuong) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, chiTietPhieuXuat.getMaPhieuXuat());
            preparedStatement.setInt(2, chiTietPhieuXuat.getMaSanPham());
            preparedStatement.setInt(3, chiTietPhieuXuat.getSoLuong());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateChiTietPhieuXuat(tbl_ChiTietPhieuXuat chiTietPhieuXuat) {
        try {
            String sql = "UPDATE ChiTietPhieuXuat SET MaPhieuXuat = ?, MaSanPham = ?, SoLuong = ? WHERE MaChiTietPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, chiTietPhieuXuat.getMaPhieuXuat());
            preparedStatement.setInt(2, chiTietPhieuXuat.getMaSanPham());
            preparedStatement.setInt(3, chiTietPhieuXuat.getSoLuong());
            preparedStatement.setInt(4, chiTietPhieuXuat.getMaChiTietPhieuXuat());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteChiTietPhieuXuat(int id) {
        try {
            String sql = "DELETE FROM ChiTietPhieuXuat WHERE MaChiTietPhieuXuat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<tbl_ChiTietPhieuXuat> getDanhSachChiTietPhieuXuat() {
        List<tbl_ChiTietPhieuXuat> danhSachChiTietPhieuXuat = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ChiTietPhieuXuat";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tbl_ChiTietPhieuXuat chiTietPhieuXuat = new tbl_ChiTietPhieuXuat();
                chiTietPhieuXuat.setMaChiTietPhieuXuat(resultSet.getInt("MaChiTietPhieuXuat"));
                chiTietPhieuXuat.setMaPhieuXuat(resultSet.getInt("MaPhieuXuat"));
                chiTietPhieuXuat.setMaSanPham(resultSet.getInt("MaSanPham"));
                chiTietPhieuXuat.setSoLuong(resultSet.getInt("SoLuong"));

                danhSachChiTietPhieuXuat.add(chiTietPhieuXuat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachChiTietPhieuXuat;
    }
}