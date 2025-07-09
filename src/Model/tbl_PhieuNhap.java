package Model;

import java.sql.Date;

public class tbl_PhieuNhap {
    private int maPhieuNhap;
    private Date ngayNhap;

    public tbl_PhieuNhap(int maPhieuNhap, Date ngayNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
    }

    public tbl_PhieuNhap() {}

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    @Override
    public String toString() {
        return "Mã Phiếu Nhập: " + maPhieuNhap + ", Ngày Nhập: " + ngayNhap;
    }

}