package Model;

public class tbl_ChiTietPhieuXuat {
    private int maChiTietPhieuXuat;
    private int maPhieuXuat;
    private int maSanPham;
    private int soLuong;

    public tbl_ChiTietPhieuXuat(int maChiTietPhieuXuat, int maPhieuXuat, int maSanPham, int soLuong) {
        this.maChiTietPhieuXuat = maChiTietPhieuXuat;
        this.maPhieuXuat = maPhieuXuat;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
    }

    public tbl_ChiTietPhieuXuat() {}

    public int getMaChiTietPhieuXuat() {
        return maChiTietPhieuXuat;
    }

    public void setMaChiTietPhieuXuat(int maChiTietPhieuXuat) {
        this.maChiTietPhieuXuat = maChiTietPhieuXuat;
    }

    public int getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(int maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
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