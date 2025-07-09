package View;

import Controller.PhieuNhapCtrl;
import Controller.SanPhamCtrl;
import Controller.ChiTietPhieuNhapCtrl;
import Model.tbl_PhieuNhap;
import Model.tbl_SanPham;
import Model.tbl_ChiTietPhieuNhap;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
// import javax.swing.table.DefaultTableModel;

public class PhieuNhapView {
    private JFrame frame;
    private PhieuNhapCtrl phieuNhapCtrl;
    private SanPhamCtrl sanPhamCtrl;
    private ChiTietPhieuNhapCtrl chiTietPhieuNhapCtrl;
    
    public PhieuNhapView() {
        frame = new JFrame("Quản lý Phiếu Nhập");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        phieuNhapCtrl = new PhieuNhapCtrl();
        sanPhamCtrl = new SanPhamCtrl();
        chiTietPhieuNhapCtrl = new ChiTietPhieuNhapCtrl();
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
                JDialog dialog = new JDialog(frame, "Thêm Phiếu Nhập", true);
                dialog.setSize(300, 150);
                dialog.setLayout(new GridLayout(2, 2));

                JLabel ngayNhapLabel = new JLabel("Ngày nhập:");
                JTextField ngayNhapField = new JTextField();

                dialog.add(ngayNhapLabel);
                dialog.add(ngayNhapField);

                JButton addButton = new JButton("Thêm");
                dialog.add(new JLabel());
                dialog.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ngayNhapText = ngayNhapField.getText();

                        if (ngayNhapText.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "Ngày nhập không được để trống!");
                            return;
                        }

                        try {
                            java.sql.Date ngayNhap = java.sql.Date.valueOf(ngayNhapText);

                            tbl_PhieuNhap phieuNhap = new tbl_PhieuNhap(0, ngayNhap);
                            phieuNhapCtrl.addPhieuNhap(phieuNhap);
                            JOptionPane.showMessageDialog(dialog, "Đã thêm phiếu nhập mới.");
                            dialog.dispose();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(dialog, "Ngày nhập không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd.");
                        }
                    }
                });

                dialog.setVisible(true);
            }
        });
        
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Xóa Phiếu Nhập", true);
                dialog.setSize(500, 400);
                dialog.setLayout(new BorderLayout());

                List<tbl_PhieuNhap> danhSachPhieuNhap = phieuNhapCtrl.getDanhSachPhieuNhap();
                JList<tbl_PhieuNhap> list = new JList<>(danhSachPhieuNhap.toArray(new tbl_PhieuNhap[0]));
                dialog.add(new JScrollPane(list), BorderLayout.CENTER);

                JButton xoaButton = new JButton("Xóa");
                xoaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tbl_PhieuNhap phieuNhap = list.getSelectedValue();

                        if (phieuNhap != null) {
                            phieuNhapCtrl.deletePhieuNhap(phieuNhap.getMaPhieuNhap());
                            danhSachPhieuNhap.remove(phieuNhap);
                            list.setListData(danhSachPhieuNhap.toArray(new tbl_PhieuNhap[0]));
                            JOptionPane.showMessageDialog(dialog, "Phiếu nhập đã được xóa.");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn phiếu nhập để xóa.");
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
                List<tbl_PhieuNhap> danhSachPhieuNhap = phieuNhapCtrl.getDanhSachPhieuNhap();

                String[] arrPhieuNhap = new String[danhSachPhieuNhap.size()];
                for (int i = 0; i < danhSachPhieuNhap.size(); i++) {
                    tbl_PhieuNhap phieuNhap = danhSachPhieuNhap.get(i);
                    arrPhieuNhap[i] = "Mã: " + phieuNhap.getMaPhieuNhap() + ", Ngày nhập: " + phieuNhap.getNgayNhap();
                }

                String selectedPhieuNhap = (String) JOptionPane.showInputDialog(frame, "Chọn một phiếu nhập để chỉnh sửa:", "Chỉnh sửa phiếu nhập", JOptionPane.PLAIN_MESSAGE, null, arrPhieuNhap, arrPhieuNhap[0]);

                if (selectedPhieuNhap != null) {
                    String[] parts = selectedPhieuNhap.split(", ");
                    int maPhieuNhap = Integer.parseInt(parts[0].split(": ")[1]);

                    tbl_PhieuNhap phieuNhap = danhSachPhieuNhap.stream().filter(p -> p.getMaPhieuNhap() == maPhieuNhap).findFirst().orElse(null);

                    JTextField ngayNhapField = new JTextField(phieuNhap.getNgayNhap().toString());

                    JPanel updatePanel = new JPanel(new GridLayout(2, 2));
                    updatePanel.add(new JLabel("Ngày nhập:"));
                    updatePanel.add(ngayNhapField);

                    int result = JOptionPane.showConfirmDialog(frame, updatePanel, "Cập nhật phiếu nhập", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        phieuNhap.setNgayNhap(java.sql.Date.valueOf(ngayNhapField.getText()));
                        phieuNhapCtrl.updatePhieuNhap(phieuNhap);
                        JOptionPane.showMessageDialog(frame, "Phiếu nhập đã được cập nhật.");
                    }
                }
            }
        });
        
        xemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<tbl_PhieuNhap> danhSachPhieuNhap = phieuNhapCtrl.getDanhSachPhieuNhap();

                String[] columnNames = {"Mã Phiếu Nhập", "Ngày Nhập"};
                Object[][] data = new Object[danhSachPhieuNhap.size()][2];

                for (int i = 0; i < danhSachPhieuNhap.size(); i++) {
                    tbl_PhieuNhap phieuNhap = danhSachPhieuNhap.get(i);
                    data[i][0] = phieuNhap.getMaPhieuNhap();
                    data[i][1] = phieuNhap.getNgayNhap();
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
                            int maPhieuNhap = (int) table.getValueAt(row, 0);

                            tbl_PhieuNhap phieuNhap = phieuNhapCtrl.getPhieuNhapById(maPhieuNhap);
                            if (phieuNhap != null) {
                                showPhieuNhapDetail(phieuNhap);
                            }
                        }
                    }
                });

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JDialog dialog = new JDialog(frame, "Danh sách Phiếu Nhập (Nhấn vào Phiếu để xem chi tiết)", true);
                dialog.add(scrollPane);
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });
    }
    
    private void showPhieuNhapDetail(tbl_PhieuNhap phieuNhap) {
        JDialog dialog = new JDialog(frame, "Chi Tiết Phiếu Nhập", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        String[] columnNames = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá", "Thành Tiền"};
        List<tbl_SanPham> danhSachSanPham = sanPhamCtrl.getSanPhamByPhieuNhap(phieuNhap.getMaPhieuNhap());
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
        JLabel ngayNhapLabel = new JLabel("Phiếu: " + phieuNhap.getMaPhieuNhap() + ", " + "Ngày Nhập: " + phieuNhap.getNgayNhap());
        bottomPanel.add(ngayNhapLabel);

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
                    tbl_ChiTietPhieuNhap chiTietPhieuNhap = new tbl_ChiTietPhieuNhap(0, phieuNhap.getMaPhieuNhap(), maSanPham, soLuong);
                    chiTietPhieuNhapCtrl.addChiTietPhieuNhap(chiTietPhieuNhap);
                    JOptionPane.showMessageDialog(dialog, "Sản phẩm đã được thêm vào hóa đơn.");
                    dialog.dispose();
                    showPhieuNhapDetail(phieuNhap);
                    
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
