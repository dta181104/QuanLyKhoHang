package DAO;

import Model.tbl_ChiTietPhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO {
    private Connection conn;

    public ChiTietPhieuNhapDAO() {
        this.conn = DatabaseConnection.getConnection(); // Sử dụng DatabaseConnection
    }

    public tbl_ChiTietPhieuNhap getChiTietPhieuNhapById(int id) {
        tbl_ChiTietPhieuNhap chiTietPhieuNhap = null;

        try {
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE MaChiTietPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chiTietPhieuNhap = new tbl_ChiTietPhieuNhap();
                chiTietPhieuNhap.setMaChiTietPhieuNhap(resultSet.getInt("MaChiTietPhieuNhap"));
                chiTietPhieuNhap.setMaPhieuNhap(resultSet.getInt("MaPhieuNhap"));
                chiTietPhieuNhap.setMaSanPham(resultSet.getInt("MaSanPham"));
                chiTietPhieuNhap.setSoLuong(resultSet.getInt("SoLuong"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chiTietPhieuNhap;
    }

    public void addChiTietPhieuNhap(tbl_ChiTietPhieuNhap chiTietPhieuNhap) {
        try {
            String sql = "INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaSanPham, SoLuong) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, chiTietPhieuNhap.getMaPhieuNhap());
            preparedStatement.setInt(2, chiTietPhieuNhap.getMaSanPham());
            preparedStatement.setInt(3, chiTietPhieuNhap.getSoLuong());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateChiTietPhieuNhap(tbl_ChiTietPhieuNhap chiTietPhieuNhap) {
        try {
            String sql = "UPDATE ChiTietPhieuNhap SET MaPhieuNhap = ?, MaSanPham = ?, SoLuong = ? WHERE MaChiTietPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, chiTietPhieuNhap.getMaPhieuNhap());
            preparedStatement.setInt(2, chiTietPhieuNhap.getMaSanPham());
            preparedStatement.setInt(3, chiTietPhieuNhap.getSoLuong());
            preparedStatement.setInt(4, chiTietPhieuNhap.getMaChiTietPhieuNhap());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteChiTietPhieuNhap(int id) {
        try {
            String sql = "DELETE FROM ChiTietPhieuNhap WHERE MaChiTietPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<tbl_ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        List<tbl_ChiTietPhieuNhap> danhSachChiTietPhieuNhap = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ChiTietPhieuNhap";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tbl_ChiTietPhieuNhap chiTietPhieuNhap = new tbl_ChiTietPhieuNhap();
                chiTietPhieuNhap.setMaChiTietPhieuNhap(resultSet.getInt("MaChiTietPhieuNhap"));
                chiTietPhieuNhap.setMaPhieuNhap(resultSet.getInt("MaPhieuNhap"));
                chiTietPhieuNhap.setMaSanPham(resultSet.getInt("MaSanPham"));
                chiTietPhieuNhap.setSoLuong(resultSet.getInt("SoLuong"));

                danhSachChiTietPhieuNhap.add(chiTietPhieuNhap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachChiTietPhieuNhap;
    }
}