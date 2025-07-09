package Model;

public class tbl_ChiTietPhieuNhap {
    private int maChiTietPhieuNhap;
    private int maPhieuNhap;
    private int maSanPham;
    private int soLuong;

    public tbl_ChiTietPhieuNhap(int maChiTietPhieuNhap, int maPhieuNhap, int maSanPham, int soLuong) {
        this.maChiTietPhieuNhap = maChiTietPhieuNhap;
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public tbl_ChiTietPhieuNhap() {}

    public int getMaChiTietPhieuNhap() {
        return maChiTietPhieuNhap;
    }

    public void setMaChiTietPhieuNhap(int maChiTietPhieuNhap) {
        this.maChiTietPhieuNhap = maChiTietPhieuNhap;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}