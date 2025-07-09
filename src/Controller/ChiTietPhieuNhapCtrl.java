package Controller;

import DAO.ChiTietPhieuNhapDAO;
import Model.tbl_ChiTietPhieuNhap;
import java.util.List;

public class ChiTietPhieuNhapCtrl {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ChiTietPhieuNhapCtrl() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    }

    public boolean isMaChiTietPhieuNhapExist(int maChiTietPhieuNhap) {
        tbl_ChiTietPhieuNhap chiTietPhieuNhap = chiTietPhieuNhapDAO.getChiTietPhieuNhapById(maChiTietPhieuNhap);
        return chiTietPhieuNhap != null;
    }

    public tbl_ChiTietPhieuNhap getChiTietPhieuNhapById(int maChiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.getChiTietPhieuNhapById(maChiTietPhieuNhap);
    }

    public List<tbl_ChiTietPhieuNhap> getDanhSachChiTietPhieuNhap() {
        return chiTietPhieuNhapDAO.getDanhSachChiTietPhieuNhap();
    }

    public void addChiTietPhieuNhap(tbl_ChiTietPhieuNhap chiTietPhieuNhap) {
        chiTietPhieuNhapDAO.addChiTietPhieuNhap(chiTietPhieuNhap);
    }

    public void updateChiTietPhieuNhap(tbl_ChiTietPhieuNhap chiTietPhieuNhap) {
        chiTietPhieuNhapDAO.updateChiTietPhieuNhap(chiTietPhieuNhap);
    }

    public void deleteChiTietPhieuNhap(int maChiTietPhieuNhap) {
        chiTietPhieuNhapDAO.deleteChiTietPhieuNhap(maChiTietPhieuNhap);
    }
}