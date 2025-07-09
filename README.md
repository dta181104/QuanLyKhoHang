# Quản Lý Kho Hàng

Dự án Java quản lý kho hàng, sử dụng giao diện Java Swing và kết nối SQL Server để lưu trữ dữ liệu.

## Tính năng

- Quản lý sản phẩm, nhập/xuất kho
- Tìm kiếm sản phẩm
- Thống kê tồn kho
- Giao diện đơn giản, dễ dùng

## Công nghệ sử dụng

- Java (JDK 8+)
- Java Swing
- SQL Server
- JDBC (Java Database Connectivity)
- [dotenv-java](https://github.com/cdimascio/dotenv-java) để quản lý biến môi trường

## Cài đặt
- chạy QuanLyKhoHang.sql trong SQL Server Management Studio

```bash
git clone https://github.com/dta181104/QuanLyKhoHang.git
cd QuanLyKhoHang
cp .env.example .env
```

- mở file .env rồi thay thế username và password SQL Server của bạn
- run file src/Main.java