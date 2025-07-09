package DAO;

import Model.tbl_SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private Connection conn;

    public SanPhamDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public tbl_SanPham getSanPhamById(int id) {
        tbl_SanPham sanPham = null;

        try {
            String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sanPham = new tbl_SanPham();
                sanPham.setMaSanPham(resultSet.getInt("MaSanPham"));
                sanPham.setTenSanPham(resultSet.getString("TenSanPham"));
                sanPham.setSoLuong(resultSet.getInt("SoLuong"));
                sanPham.setGia(resultSet.getInt("Gia"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sanPham;
    }

    public void addSanPham(tbl_SanPham sanPham) {
        try {
            String sql = "INSERT INTO SanPham (TenSanPham, SoLuong, Gia) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getTenSanPham());
            preparedStatement.setInt(2, sanPham.getSoLuong());
            preparedStatement.setInt(3, sanPham.getGia());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateSanPham(tbl_SanPham sanPham) {
        try {
            String sql = "UPDATE SanPham SET TenSanPham = ?, SoLuong = ?, Gia = ? WHERE MaSanPham = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getTenSanPham());
            preparedStatement.setInt(2, sanPham.getSoLuong());
            preparedStatement.setInt(3, sanPham.getGia());
            preparedStatement.setInt(4, sanPham.getMaSanPham());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteSanPham(int id) {
        try {
            String sql = "DELETE FROM SanPham WHERE MaSanPham = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<tbl_SanPham> getDanhSachSanPham() {
        List<tbl_SanPham> danhSachSanPham = new ArrayList<>();

        try {
            String sql = "SELECT * FROM SanPham";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tbl_SanPham sanPham = new tbl_SanPham();
                sanPham.setMaSanPham(resultSet.getInt("MaSanPham"));
                sanPham.setTenSanPham(resultSet.getString("TenSanPham"));
                sanPham.setSoLuong(resultSet.getInt("SoLuong"));
                sanPham.setGia(resultSet.getInt("Gia"));

                danhSachSanPham.add(sanPham);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachSanPham;
    }
    
    private List<tbl_SanPham> getSanPhamByPhieu(String sql, int maPhieu) {
        List<tbl_SanPham> danhSachSanPham = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, maPhieu);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    tbl_SanPham sanPham = new tbl_SanPham();
                    sanPham.setMaSanPham(resultSet.getInt("MaSanPham"));
                    sanPham.setTenSanPham(resultSet.getString("TenSanPham"));
                    sanPham.setSoLuong(resultSet.getInt("SoLuong"));
                    sanPham.setGia(resultSet.getInt("Gia"));
                    sanPham.setThanhTien(resultSet.getInt("ThanhTien"));

                    danhSachSanPham.add(sanPham);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return danhSachSanPham;
    }
    
    public List<tbl_SanPham> getSanPhamByPhieuNhap(int maPhieuNhap) {
        String sql = "SELECT S.MaSanPham, S.TenSanPham, CTPN.SoLuong, S.Gia, (CTPN.SoLuong * S.Gia) AS ThanhTien " +
                     "FROM SanPham S " +
                     "JOIN ChiTietPhieuNhap CTPN ON S.MaSanPham = CTPN.MaSanPham " +
                     "WHERE CTPN.MaPhieuNhap = ?";
        return getSanPhamByPhieu(sql, maPhieuNhap);
    }

    public List<tbl_SanPham> getSanPhamByPhieuXuat(int maPhieuXuat) {
        String sql = "SELECT S.MaSanPham, S.TenSanPham, CTPX.SoLuong, S.Gia, (CTPX.SoLuong * S.Gia) AS ThanhTien " +
                     "FROM SanPham S " +
                     "JOIN ChiTietPhieuXuat CTPX ON S.MaSanPham = CTPX.MaSanPham " +
                     "WHERE CTPX.MaPhieuXuat = ?";
        return getSanPhamByPhieu(sql, maPhieuXuat);
    }

}