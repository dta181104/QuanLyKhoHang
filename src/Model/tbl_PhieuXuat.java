package Model;

import java.sql.Date;

public class tbl_PhieuXuat {
    private int maPhieuXuat;
    private Date ngayXuat;

    public tbl_PhieuXuat(int maPhieuXuat, Date ngayXuat) {
        this.maPhieuXuat = maPhieuXuat;
        this.ngayXuat = ngayXuat;
    }

    public tbl_PhieuXuat() {}

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
    
    @Override
    public String toString() {
        return "Mã Phiếu Xuất: " + maPhieuXuat + ", Ngày Xuất: " + ngayXuat;
    }
}