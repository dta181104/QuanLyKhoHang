package Controller;

import DAO.ChiTietPhieuXuatDAO;
import Model.tbl_ChiTietPhieuXuat;
import java.util.List;

public class ChiTietPhieuXuatCtrl {
    private ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;

    public ChiTietPhieuXuatCtrl() {
        chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
    }

    public boolean isMaChiTietPhieuXuatExist(int maChiTietPhieuXuat) {
        tbl_ChiTietPhieuXuat chiTietPhieuXuat = chiTietPhieuXuatDAO.getChiTietPhieuXuatById(maChiTietPhieuXuat);
        return chiTietPhieuXuat != null;
    }

    public tbl_ChiTietPhieuXuat getChiTietPhieuXuatById(int maChiTietPhieuXuat) {
        return chiTietPhieuXuatDAO.getChiTietPhieuXuatById(maChiTietPhieuXuat);
    }

    public List<tbl_ChiTietPhieuXuat> getDanhSachChiTietPhieuXuat() {
        return chiTietPhieuXuatDAO.getDanhSachChiTietPhieuXuat();
    }

    public void addChiTietPhieuXuat(tbl_ChiTietPhieuXuat chiTietPhieuXuat) {
        chiTietPhieuXuatDAO.addChiTietPhieuXuat(chiTietPhieuXuat);
    }

    public void updateChiTietPhieuXuat(tbl_ChiTietPhieuXuat chiTietPhieuXuat) {
        chiTietPhieuXuatDAO.updateChiTietPhieuXuat(chiTietPhieuXuat);
    }

    public void deleteChiTietPhieuXuat(int maChiTietPhieuXuat) {
        chiTietPhieuXuatDAO.deleteChiTietPhieuXuat(maChiTietPhieuXuat);
    }
}