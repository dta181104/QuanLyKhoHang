package Controller;

import DAO.PhieuNhapDAO;
import Model.tbl_PhieuNhap;
import java.util.List;

public class PhieuNhapCtrl {
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapCtrl() {
        phieuNhapDAO = new PhieuNhapDAO();
    }

    public boolean isMaPhieuNhapExist(int maPhieuNhap) {
        tbl_PhieuNhap phieuNhap = phieuNhapDAO.getPhieuNhapById(maPhieuNhap);
        return phieuNhap != null;
    }

    public tbl_PhieuNhap getPhieuNhapById(int maPhieuNhap) {
        return phieuNhapDAO.getPhieuNhapById(maPhieuNhap);
    }

    public List<tbl_PhieuNhap> getDanhSachPhieuNhap() {
        return phieuNhapDAO.getDanhSachPhieuNhap();
    }

    public void addPhieuNhap(tbl_PhieuNhap phieuNhap) {
        phieuNhapDAO.addPhieuNhap(phieuNhap);
    }

    public void updatePhieuNhap(tbl_PhieuNhap phieuNhap) {
        phieuNhapDAO.updatePhieuNhap(phieuNhap);
    }

    public void deletePhieuNhap(int maPhieuNhap) {
        phieuNhapDAO.deletePhieuNhap(maPhieuNhap);
    }
}