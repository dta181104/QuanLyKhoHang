package DAO;

import Model.tbl_PhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    private Connection conn;

    public PhieuNhapDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public tbl_PhieuNhap getPhieuNhapById(int id) {
        tbl_PhieuNhap phieuNhap = null;

        try {
            String sql = "SELECT * FROM PhieuNhap WHERE MaPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                phieuNhap = new tbl_PhieuNhap();
                phieuNhap.setMaPhieuNhap(resultSet.getInt("MaPhieuNhap"));
                phieuNhap.setNgayNhap(resultSet.getDate("NgayNhap"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return phieuNhap;
    }

    public void addPhieuNhap(tbl_PhieuNhap phieuNhap) {
        try {
            String sql = "INSERT INTO PhieuNhap (NgayNhap) VALUES (?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, phieuNhap.getNgayNhap());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePhieuNhap(tbl_PhieuNhap phieuNhap) {
        try {
            String sql = "UPDATE PhieuNhap SET NgayNhap = ? WHERE MaPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, phieuNhap.getNgayNhap());
            preparedStatement.setInt(2, phieuNhap.getMaPhieuNhap());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePhieuNhap(int id) {
        try {
            String sql = "DELETE FROM PhieuNhap WHERE MaPhieuNhap = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<tbl_PhieuNhap> getDanhSachPhieuNhap() {
        List<tbl_PhieuNhap> danhSachPhieuNhap = new ArrayList<>();

        try {
            String sql = "SELECT * FROM PhieuNhap";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tbl_PhieuNhap phieuNhap = new tbl_PhieuNhap();
                phieuNhap.setMaPhieuNhap(resultSet.getInt("MaPhieuNhap"));
                phieuNhap.setNgayNhap(resultSet.getDate("NgayNhap"));

                danhSachPhieuNhap.add(phieuNhap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachPhieuNhap;
    }
}