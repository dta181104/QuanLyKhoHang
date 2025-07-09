CREATE DATABASE KhoHang;
GO

USE KhoHang;
GO

CREATE TABLE SanPham (
    MaSanPham INT PRIMARY KEY IDENTITY(1,1), 
    TenSanPham NVARCHAR(100) NOT NULL,       
    SoLuong INT NOT NULL,                     
    Gia INT NOT NULL               
);
GO

CREATE TABLE PhieuNhap (
    MaPhieuNhap INT PRIMARY KEY IDENTITY(1,1),  
    NgayNhap DATETIME NOT NULL                 
);
GO

CREATE TABLE ChiTietPhieuNhap (
    MaChiTietPhieuNhap INT PRIMARY KEY IDENTITY(1,1),  
    MaPhieuNhap INT NOT NULL,                           
    MaSanPham INT NOT NULL,                             
    SoLuong INT NOT NULL,                               
    FOREIGN KEY (MaPhieuNhap) REFERENCES PhieuNhap(MaPhieuNhap),                 
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)                         
);
GO

CREATE TABLE PhieuXuat (
    MaPhieuXuat INT PRIMARY KEY IDENTITY(1,1),  
    NgayXuat DATETIME NOT NULL                 
);
GO

CREATE TABLE ChiTietPhieuXuat (
    MaChiTietPhieuXuat INT PRIMARY KEY IDENTITY(1,1), 
    MaPhieuXuat INT NOT NULL,                         
    MaSanPham INT NOT NULL,                            
    SoLuong INT NOT NULL,                             
    FOREIGN KEY (MaPhieuXuat) REFERENCES PhieuXuat(MaPhieuXuat),               
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)                       
);
GO

CREATE PROCEDURE NhapHang
    @MaSanPham INT,
    @SoLuong INT
AS
BEGIN
    UPDATE SanPham
    SET SoLuong = SoLuong + @SoLuong
    WHERE MaSanPham = @MaSanPham;
END;
GO

CREATE PROCEDURE XuatHang
    @MaSanPham INT,
    @SoLuong INT
AS
BEGIN
    -- Kiểm tra xem số lượng có đủ để xuất hay không
    IF EXISTS (SELECT * FROM SanPham WHERE MaSanPham = @MaSanPham AND SoLuong >= @SoLuong)
    BEGIN
        UPDATE SanPham
        SET SoLuong = SoLuong - @SoLuong
        WHERE MaSanPham = @MaSanPham;
    END
    ELSE
    BEGIN
        PRINT 'Không đủ hàng để xuất.';
    END
END;
GO

INSERT INTO SanPham (TenSanPham, SoLuong, Gia) VALUES
(N'Ổ cắm', 25, 35000),       
(N'Phích cắm', 50, 10000),   
(N'Dây điện', 15, 50000),     
(N'Bóng đèn', 40, 110000),     
(N'Quạt điện', 10, 315000),    
(N'Aptomat', 30, 45000),      
(N'Chuông cửa', 20, 155000),   
(N'Máy sấy tóc', 5, 235000), 
(N'Camera giám sát', 8, 575000); 
GO

INSERT INTO PhieuNhap (NgayNhap) VALUES
('2024-01-01'), 
('2024-01-05'), 
('2024-01-10'); 
GO

INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaSanPham, SoLuong) VALUES
(1, 1, 10),  -- Phiếu nhập 1, sản phẩm Ổ cắm, số lượng 10
(1, 2, 5),   -- Phiếu nhập 1, sản phẩm Phích cắm, số lượng 5
(2, 3, 8),   -- Phiếu nhập 2, sản phẩm Dây điện, số lượng 8
(2, 4, 12),  -- Phiếu nhập 2, sản phẩm Bóng đèn, số lượng 12
(3, 5, 4),   -- Phiếu nhập 3, sản phẩm Quạt điện, số lượng 4
(3, 6, 7);   -- Phiếu nhập 3, sản phẩm Aptomat, số lượng 7
GO
EXEC NhapHang @MaSanPham = 1, @SoLuong = 10;
EXEC NhapHang @MaSanPham = 2, @SoLuong = 5;
EXEC NhapHang @MaSanPham = 3, @SoLuong = 8;
EXEC NhapHang @MaSanPham = 4, @SoLuong = 12;
EXEC NhapHang @MaSanPham = 5, @SoLuong = 4;
EXEC NhapHang @MaSanPham = 6, @SoLuong = 7;

INSERT INTO PhieuXuat (NgayXuat) VALUES
('2025-01-15'),
('2025-01-20');
GO

INSERT INTO ChiTietPhieuXuat (MaPhieuXuat, MaSanPham, SoLuong) VALUES
(1, 1, 3),   -- Phiếu xuất 1, sản phẩm Ổ cắm, số lượng 3
(1, 2, 2),   -- Phiếu xuất 1, sản phẩm Phích cắm, số lượng 2
(1, 3, 5),   -- Phiếu xuất 1, sản phẩm Dây điện, số lượng 5
(2, 4, 1),   -- Phiếu xuất 2, sản phẩm Bóng đèn, số lượng 1
(2, 5, 1);   -- Phiếu xuất 2, sản phẩm Quạt điện, số lượng 1
GO
EXEC XuatHang @MaSanPham = 1, @SoLuong = 3;
EXEC XuatHang @MaSanPham = 2, @SoLuong = 2;
EXEC XuatHang @MaSanPham = 3, @SoLuong = 5;
EXEC XuatHang @MaSanPham = 4, @SoLuong = 1;
EXEC XuatHang @MaSanPham = 5, @SoLuong = 1;

SELECT S.MaSanPham, S.TenSanPham, CTPN.SoLuong, S.Gia
FROM SanPham S
JOIN ChiTietPhieuNhap CTPN ON S.MaSanPham = CTPN.MaSanPham
WHERE CTPN.MaPhieuNhap = 1

SELECT S.MaSanPham, S.TenSanPham, CTPN.SoLuong, S.Gia ,(CTPN.SoLuong * S.Gia) AS ThanhTien
FROM SanPham S 
JOIN ChiTietPhieuNhap CTPN ON S.MaSanPham = CTPN.MaSanPham
WHERE CTPN.MaPhieuNhap = 1

SELECT S.MaSanPham, S.TenSanPham, CTPN.SoLuong, S.Gia, (CTPN.SoLuong * S.Gia) AS ThanhTien 
FROM SanPham S 
JOIN ChiTietPhieuNhap CTPN ON S.MaSanPham = CTPN.MaSanPham
WHERE CTPN.MaPhieuNhap = 1

SELECT S.MaSanPham, S.TenSanPham, CTPX.SoLuong, S.Gia, (CTPX.SoLuong * S.Gia) AS ThanhTien
FROM SanPham S
JOIN ChiTietPhieuXuat CTPX ON S.MaSanPham = CTPX.MaSanPham
WHERE CTPX.MaPhieuXuat = 1
