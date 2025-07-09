package View;

import Controller.PhieuXuatCtrl;
import Controller.SanPhamCtrl;
import Controller.ChiTietPhieuXuatCtrl;
import Model.tbl_PhieuXuat;
import Model.tbl_SanPham;
import Model.tbl_ChiTietPhieuXuat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class PhieuXuatView {
    private JFrame frame;
    private PhieuXuatCtrl phieuXuatCtrl;
    private SanPhamCtrl sanPhamCtrl;
    private ChiTietPhieuXuatCtrl chiTietPhieuXuatCtrl;
    
    public PhieuXuatView() {
        frame = new JFrame("Quản lý Phiếu Xuất");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        phieuXuatCtrl = new PhieuXuatCtrl();
        sanPhamCtrl = new SanPhamCtrl();
        chiTietPhieuXuatCtrl = new ChiTietPhieuXuatCtrl();
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton themButton = new JButton("Thêm");
        themButton.setBounds(10, 10, 80, 25);
        panel.add(themButton);
        
        JButton xoaButton = new JButton("Xóa");
        xoaButton.setBounds(100, 10, 80, 25);
        panel.add(xoaButton);
        
        JButton timCapNhatButton = new JButton("Tìm/Cập nhật");
        timCapNhatButton.setBounds(190, 10, 150, 25);
        panel.add(timCapNhatButton);
        
        JButton xemButton = new JButton("Xem danh sách");
        xemButton.setBounds(350, 10, 150, 25);
        panel.add(xemButton);

        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Thêm Phiếu Xuất", true);
                dialog.setSize(300, 150);
                dialog.setLayout(new GridLayout(2, 2));

                JLabel ngayXuatLabel = new JLabel("Ngày xuất:");
                JTextField ngayXuatField = new JTextField();

                dialog.add(ngayXuatLabel);
                dialog.add(ngayXuatField);

                JButton addButton = new JButton("Thêm");
                dialog.add(new JLabel());
                dialog.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ngayXuatText = ngayXuatField.getText();

                        if (ngayXuatText.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "Ngày xuất không được để trống!");
                            return;
                        }

                        try {
                            java.sql.Date ngayXuat = java.sql.Date.valueOf(ngayXuatText);

                            tbl_PhieuXuat phieuXuat = new tbl_PhieuXuat(0, ngayXuat);
                            phieuXuatCtrl.addPhieuXuat(phieuXuat);
                            JOptionPane.showMessageDialog(dialog, "Đã thêm phiếu xuất mới.");
                            dialog.dispose();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(dialog, "Ngày xuất không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd.");
                        }
                    }
                });

                dialog.setVisible(true);
            }
        });
        
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Xóa Phiếu Xuất", true);
                dialog.setSize(500, 400);
                dialog.setLayout(new BorderLayout());

                List<tbl_PhieuXuat> danhSachPhieuXuat = phieuXuatCtrl.getDanhSachPhieuXuat();
                JList<tbl_PhieuXuat> list = new JList<>(danhSachPhieuXuat.toArray(new tbl_PhieuXuat[0]));
                dialog.add(new JScrollPane(list), BorderLayout.CENTER);

                JButton xoaButton = new JButton("Xóa");
                xoaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tbl_PhieuXuat phieuXuat = list.getSelectedValue();

                        if (phieuXuat != null) {
                            phieuXuatCtrl.deletePhieuXuat(phieuXuat.getMaPhieuXuat());
                            danhSachPhieuXuat.remove(phieuXuat);
                            list.setListData(danhSachPhieuXuat.toArray(new tbl_PhieuXuat[0]));
                            JOptionPane.showMessageDialog(dialog, "Phiếu xuất đã được xóa.");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn phiếu xuất để xóa.");
                        }
                    }
                });

                dialog.add(xoaButton, BorderLayout.SOUTH);
                dialog.setVisible(true);
            }
        });
        
        timCapNhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<tbl_PhieuXuat> danhSachPhieuXuat = phieuXuatCtrl.getDanhSachPhieuXuat();

                String[] arrPhieuXuat = new String[danhSachPhieuXuat.size()];
                for (int i = 0; i < danhSachPhieuXuat.size(); i++) {
                    tbl_PhieuXuat phieuXuat = danhSachPhieuXuat.get(i);
                    arrPhieuXuat[i] = "Mã: " + phieuXuat.getMaPhieuXuat() + ", Ngày xuất: " + phieuXuat.getNgayXuat();
                }

                String selectedPhieuXuat = (String) JOptionPane.showInputDialog(frame, "Chọn một phiếu xuất để chỉnh sửa:", "Chỉnh sửa phiếu xuất", JOptionPane.PLAIN_MESSAGE, null, arrPhieuXuat, arrPhieuXuat[0]);

                if (selectedPhieuXuat != null) {
                    String[] parts = selectedPhieuXuat.split(", ");
                    int maPhieuXuat = Integer.parseInt(parts[0].split(": ")[1]);

                    tbl_PhieuXuat phieuXuat = danhSachPhieuXuat.stream().filter(p -> p.getMaPhieuXuat() == maPhieuXuat).findFirst().orElse(null);

                    JTextField ngayXuatField = new JTextField(phieuXuat.getNgayXuat().toString());

                    JPanel updatePanel = new JPanel(new GridLayout(2, 2));
                    updatePanel.add(new JLabel("Ngày xuất:"));
                    updatePanel.add(ngayXuatField);

                    int result = JOptionPane.showConfirmDialog(frame, updatePanel, "Cập nhật phiếu xuất", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        phieuXuat.setNgayXuat(java.sql.Date.valueOf(ngayXuatField.getText()));
                        phieuXuatCtrl.updatePhieuXuat(phieuXuat);
                        JOptionPane.showMessageDialog(frame, "Phiếu xuất đã được cập nhật.");
                    }
                }
            }
        });
        
        xemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<tbl_PhieuXuat> danhSachPhieuXuat = phieuXuatCtrl.getDanhSachPhieuXuat();

                String[] columnNames = {"Mã Phiếu Xuất", "Ngày Xuất"};
                Object[][] data = new Object[danhSachPhieuXuat.size()][2];

                for (int i = 0; i < danhSachPhieuXuat.size(); i++) {
                    tbl_PhieuXuat phieuXuat = danhSachPhieuXuat.get(i);
                    data[i][0] = phieuXuat.getMaPhieuXuat();
                    data[i][1] = phieuXuat.getNgayXuat();
                }

                JTable table = new JTable(data, columnNames);

                JTableHeader header = table.getTableHeader();
                header.setBackground(Color.LIGHT_GRAY);
                header.setForeground(Color.BLUE);
                header.setFont(new Font("Arial", Font.BOLD, 14));

                table.setShowGrid(true);
                table.setGridColor(Color.BLACK);
                table.setRowHeight(25);

                table.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        int row = table.getSelectedRow();
                        if (row != -1) {
                            int maPhieuXuat = (int) table.getValueAt(row, 0);

                            tbl_PhieuXuat phieuXuat = phieuXuatCtrl.getPhieuXuatById(maPhieuXuat);
                            if (phieuXuat != null) {
                                showPhieuXuatDetail(phieuXuat);
                            }
                        }
                    }
                });

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JDialog dialog = new JDialog(frame, "Danh sách Phiếu Xuất (Nhấn vào Phiếu để xem chi tiết)", true);
                dialog.add(scrollPane);
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });
    }
    
    private void showPhieuXuatDetail(tbl_PhieuXuat phieuXuat) {
        JDialog dialog = new JDialog(frame, "Chi Tiết Phiếu Xuất", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        String[] columnNames = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá", "Thành Tiền"};
        List<tbl_SanPham> danhSachSanPham = sanPhamCtrl.getSanPhamByPhieuXuat(phieuXuat.getMaPhieuXuat());
        Object[][] data = new Object[danhSachSanPham.size() + 1][5];

        int tongTien = 0;

        for (int i = 0; i < danhSachSanPham.size(); i++) {
            tbl_SanPham sanPham = danhSachSanPham.get(i);
            int thanhTien = sanPham.getSoLuong() * sanPham.getGia();
            data[i][0] = sanPham.getMaSanPham();
            data[i][1] = sanPham.getTenSanPham();
            data[i][2] = sanPham.getSoLuong();
            data[i][3] = sanPham.getGia();
            data[i][4] = thanhTien;
            tongTien += thanhTien;
        }

        data[danhSachSanPham.size()][0] = "";
        data[danhSachSanPham.size()][1] = "";
        data[danhSachSanPham.size()][2] = "";
        data[danhSachSanPham.size()][3] = "Tổng Tiền:";
        data[danhSachSanPham.size()][4] = tongTien;

        JTable table = new JTable(data, columnNames);

        DefaultTableCellRenderer rightAlignRenderer = new DefaultTableCellRenderer();
        rightAlignRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightAlignRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightAlignRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(rightAlignRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dialog.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JLabel ngayXuatLabel = new JLabel("Phiếu: " + phieuXuat.getMaPhieuXuat() + ", " + "Ngày Xuất: " + phieuXuat.getNgayXuat());
        bottomPanel.add(ngayXuatLabel);

        dialog.add(bottomPanel, BorderLayout.SOUTH);

        JPanel addProductPanel = new JPanel();
        JTextField maSanPhamField = new JTextField(10);
        JTextField soLuongField = new JTextField(5);
        JButton addProductButton = new JButton("Thêm Sản Phẩm");

        addProductPanel.add(new JLabel("Mã Sản Phẩm:"));
        addProductPanel.add(maSanPhamField);
        addProductPanel.add(new JLabel("Số Lượng:"));
        addProductPanel.add(soLuongField);
        addProductPanel.add(addProductButton);

        dialog.add(addProductPanel, BorderLayout.NORTH);

        addProductButton.addActionListener(e -> {
            try {
                int maSanPham = Integer.parseInt(maSanPhamField.getText());
                int soLuong = Integer.parseInt(soLuongField.getText());

                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Số lượng phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tbl_SanPham sanPham = sanPhamCtrl.getSanPhamById(maSanPham);
                if (sanPham != null) {
                    tbl_SanPham sanPhamMoi = new tbl_SanPham();
                    sanPhamMoi.setMaSanPham(sanPham.getMaSanPham());
                    sanPhamMoi.setTenSanPham(sanPham.getTenSanPham());
                    sanPhamMoi.setSoLuong(soLuong);
                    sanPhamMoi.setGia(sanPham.getGia());

                    danhSachSanPham.add(sanPhamMoi);
                    tbl_ChiTietPhieuXuat chiTietPhieuXuat = new tbl_ChiTietPhieuXuat(0, phieuXuat.getMaPhieuXuat(), maSanPham, soLuong);
                    chiTietPhieuXuatCtrl.addChiTietPhieuXuat(chiTietPhieuXuat);
                    JOptionPane.showMessageDialog(dialog, "Sản phẩm đã được thêm vào phiếu xuất.");
                    dialog.dispose();
                    showPhieuXuatDetail(phieuXuat);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Sản phẩm không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số lượng hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
