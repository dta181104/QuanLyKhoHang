package Controller;

import DAO.PhieuXuatDAO;
import Model.tbl_PhieuXuat;
import java.util.List;

public class PhieuXuatCtrl {
    private PhieuXuatDAO phieuXuatDAO;

    public PhieuXuatCtrl() {
        phieuXuatDAO = new PhieuXuatDAO();
    }

    public boolean isMaPhieuXuatExist(int maPhieuXuat) {
        tbl_PhieuXuat phieuXuat = phieuXuatDAO.getPhieuXuatById(maPhieuXuat);
        return phieuXuat != null;
    }

    public tbl_PhieuXuat getPhieuXuatById(int maPhieuXuat) {
        return phieuXuatDAO.getPhieuXuatById(maPhieuXuat);
    }

    public List<tbl_PhieuXuat> getDanhSachPhieuXuat() {
        return phieuXuatDAO.getDanhSachPhieuXuat();
    }

    public void addPhieuXuat(tbl_PhieuXuat phieuXuat) {
        phieuXuatDAO.addPhieuXuat(phieuXuat);
    }

    public void updatePhieuXuat(tbl_PhieuXuat phieuXuat) {
        phieuXuatDAO.updatePhieuXuat(phieuXuat);
    }

    public void deletePhieuXuat(int maPhieuXuat) {
        phieuXuatDAO.deletePhieuXuat(maPhieuXuat);
    }
}