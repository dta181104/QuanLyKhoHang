package Controller;

import DAO.SanPhamDAO;
import Model.tbl_SanPham;
import java.util.List;

public class SanPhamCtrl {
    private SanPhamDAO sanPhamDAO;

    public SanPhamCtrl() {
        sanPhamDAO = new SanPhamDAO();
    }

    public boolean isMaSanPhamExist(int maSanPham) {
        tbl_SanPham sanPham = sanPhamDAO.getSanPhamById(maSanPham);
        return sanPham != null;
    }

    public tbl_SanPham getSanPhamById(int maSanPham) {
        return sanPhamDAO.getSanPhamById(maSanPham);
    }

    public List<tbl_SanPham> getDanhSachSanPham() {
        return sanPhamDAO.getDanhSachSanPham();
    }
    
    public List<tbl_SanPham> getSanPhamByPhieuNhap(int maPhieuNhap) {
        return sanPhamDAO.getSanPhamByPhieuNhap(maPhieuNhap);
    }
    
    public List<tbl_SanPham> getSanPhamByPhieuXuat(int maPhieuXuat) {
        return sanPhamDAO.getSanPhamByPhieuXuat(maPhieuXuat);
    }

    public void addSanPham(tbl_SanPham sanPham) {
        sanPhamDAO.addSanPham(sanPham);
    }

    public void updateSanPham(tbl_SanPham sanPham) {
        sanPhamDAO.updateSanPham(sanPham);
    }

    public void deleteSanPham(int maSanPham) {
        sanPhamDAO.deleteSanPham(maSanPham);
    }
}