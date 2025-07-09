package Model;

public class tbl_SanPham {
    private int maSanPham;
    private String tenSanPham;
    private int soLuong;
    private int gia;
    private int thanhTien;
    
    public tbl_SanPham(int maSanPham, String tenSanPham, int soLuong, int gia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = 0;
    }
    
    public tbl_SanPham(int maSanPham, String tenSanPham, int soLuong, int gia, int thanhTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = thanhTien;
    }

    public tbl_SanPham() {}

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    
    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Ghi đè phương thức toString để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Mã: " + maSanPham + ", Tên: " + tenSanPham + ", Số lượng: " + soLuong + ", Giá: " + gia;
    }
}