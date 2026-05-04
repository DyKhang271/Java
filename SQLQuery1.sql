CREATE DATABASE QLNHANVIEN;
GO
USE QLNHANVIEN;
GO

-- Tạo bảng PhongBan
CREATE TABLE PhongBan (
    maPhong VARCHAR(10) PRIMARY KEY,
    tenPhong NVARCHAR(50)
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY,
    ho NVARCHAR(50),
    ten NVARCHAR(50),
    tuoi INT,
    phai BIT, -- 0: Nữ, 1: Nam
    maPhong VARCHAR(10),
    tienLuong DECIMAL(15,2),
    FOREIGN KEY (maPhong) REFERENCES PhongBan(maPhong)
);

INSERT INTO PhongBan (maPhong, tenPhong) VALUES
('PB01', N'Phòng Giám Đốc'),
('PB02', N'Phòng Kỹ Thuật'),
('PB03', N'Phòng Nhân Sự'),
('PB04', N'Phòng Tài Chính'),
('PB05', N'Phòng Kinh Doanh');

INSERT INTO NhanVien (maNV, ho, ten, tuoi, phai, maPhong, tienLuong) VALUES
('NV01', N'Trần', N'Duy Khang', 21, 1, 'PB01', 50000000), 
('NV02', N'Nguyễn', N'Hoàng Nam', 25, 1, 'PB02', 15000000),
('NV03', N'Lê', N'Thị Lan', 24, 0, 'PB03', 12000000),
('NV04', N'Phạm', N'Minh Tuấn', 30, 1, 'PB04', 18000000),
('NV05', N'Đặng', N'Thu Thảo', 27, 0, 'PB05', 14000000),
('NV06', N'Vũ', N'Gia Bảo', 29, 1, 'PB02', 16500000),
('NV07', N'Bùi', N'Kim Ngân', 23, 0, 'PB03', 11500000),
('NV08', N'Đỗ', N'Thành Vinh', 32, 1, 'PB05', 20000000),
('NV09', N'Hoàng', N'Ngọc Ánh', 26, 0, 'PB04', 13500000),
('NV10', N'Ngô', N'Quốc Huy', 28, 1, 'PB02', 15500000);
