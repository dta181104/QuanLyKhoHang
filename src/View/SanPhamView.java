package View;

import Controller.SanPhamCtrl;
import Model.tbl_SanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SanPhamView {
    private JFrame frame;
    private SanPhamCtrl sanPhamCtrl;

    public SanPhamView() {
        frame = new JFrame("Quản lý Sản Phẩm");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sanPhamCtrl = new SanPhamCtrl();

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
                JDialog dialog = new JDialog(frame, "Thêm Sản Phẩm", true);
                dialog.setSize(300, 230);
                dialog.setLayout(new GridLayout(4, 2));

                JLabel tenSanPhamLabel = new JLabel("Tên sản phẩm:");
                JTextField tenSanPhamField = new JTextField();
                JLabel soLuongLabel = new JLabel("Số lượng:");
                JTextField soLuongField = new JTextField();
                JLabel giaLabel = new JLabel("Giá:");
                JTextField giaField = new JTextField();

                dialog.add(tenSanPhamLabel);
                dialog.add(tenSanPhamField);
                dialog.add(soLuongLabel);
                dialog.add(soLuongField);
                dialog.add(giaLabel);
                dialog.add(giaField);

                JButton addButton = new JButton("Thêm");
                dialog.add(addButton);
                
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String tenSanPham = tenSanPhamField.getText();
                        int soLuong = Integer.parseInt(soLuongField.getText());
                       
                        int gia = Integer.parseInt(giaField.getText());

                        tbl_SanPham sanPham = new tbl_SanPham(0, tenSanPham, soLuong, gia);

                        sanPhamCtrl.addSanPham(sanPham);
                        JOptionPane.showMessageDialog(dialog, "Đã thêm sản phẩm mới");
                        dialog.dispose();
                    }
                });

                dialog.setVisible(true);
            }
        });
        
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Xóa Sản Phẩm", true);
                dialog.setSize(500, 500);
                dialog.setLayout(new BorderLayout());

                List<tbl_SanPham> danhSachSanPham = sanPhamCtrl.getDanhSachSanPham();
                JList<tbl_SanPham> list = new JList<>(danhSachSanPham.toArray(new tbl_SanPham[0]));
                dialog.add(new JScrollPane(list), BorderLayout.CENTER);

                JButton xoaButton = new JButton("Xóa");
                xoaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tbl_SanPham sanPham = list.getSelectedValue();

                        if (sanPham != null) {
                            sanPhamCtrl.deleteSanPham(sanPham.getMaSanPham());
                            danhSachSanPham.remove(sanPham);
                            list.setListData(danhSachSanPham.toArray(new tbl_SanPham[0]));
                            JOptionPane.showMessageDialog(dialog, "Sản phẩm đã được xóa.");
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn sản phẩm để xóa.");
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
                List<tbl_SanPham> danhSachSanPham = sanPhamCtrl.getDanhSachSanPham();

                String[] arrSanPham = new String[danhSachSanPham.size()];
                for (int i = 0; i < danhSachSanPham.size(); i++) {
                    tbl_SanPham sanPham = danhSachSanPham.get(i);
                    arrSanPham[i] = "Mã: " + sanPham.getMaSanPham() + ", Tên: " + sanPham.getTenSanPham() + ", SL: " + sanPham.getSoLuong() + ", Giá: " + sanPham.getGia();
                }

                String selectedSanPham = (String) JOptionPane.showInputDialog(frame, "Chọn một sản phẩm để chỉnh sửa:", "Chỉnh sửa sản phẩm", JOptionPane.PLAIN_MESSAGE, null, arrSanPham, arrSanPham[0]);

                if (selectedSanPham != null) {
                    String[] parts = selectedSanPham.split(", ");
                    int maSanPham = Integer.parseInt(parts[0].split(": ")[1]);

                    tbl_SanPham sanPham = danhSachSanPham.stream().filter(s -> s.getMaSanPham() == maSanPham).findFirst().orElse(null);

                    JTextField tenSanPhamField = new JTextField(sanPham.getTenSanPham());
                    JTextField soLuongField = new JTextField(String.valueOf(sanPham.getSoLuong()));
                    JTextField giaField = new JTextField(String.valueOf(sanPham.getGia()));

                    JPanel updatePanel = new JPanel(new GridLayout(4, 2));
                    updatePanel.add(new JLabel("Tên sản phẩm:"));
                    updatePanel.add(tenSanPhamField);
                    updatePanel.add(new JLabel("Số lượng:"));
                    updatePanel.add(soLuongField);
                    updatePanel.add(new JLabel("Giá:"));
                    updatePanel.add(giaField);

                    int result = JOptionPane.showConfirmDialog(frame, updatePanel, "Cập nhật sản phẩm", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        int confirm = JOptionPane.showConfirmDialog(frame, updatePanel, "Bạn chắc chắn muốn thay đổi?", JOptionPane.OK_CANCEL_OPTION);
                        if (confirm == JOptionPane.OK_OPTION) {
                        	sanPham.setTenSanPham(tenSanPhamField.getText());
                            sanPham.setSoLuong(Integer.parseInt(soLuongField.getText()));
                            sanPham.setGia(Integer.parseInt(giaField.getText()));
                        	sanPhamCtrl.updateSanPham(sanPham);
                            JOptionPane.showMessageDialog(frame, "Sản phẩm đã được cập nhật.");
                        }
                    }
                }
            }
        });
        
        xemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<tbl_SanPham> danhSachSanPham = sanPhamCtrl.getDanhSachSanPham();

                String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"};
                Object[][] data = new Object[danhSachSanPham.size()][4];

                for (int i = 0; i < danhSachSanPham.size(); i++) {
                    tbl_SanPham sanPham = danhSachSanPham.get(i);
                    data[i][0] = sanPham.getMaSanPham();
                    data[i][1] = sanPham.getTenSanPham();
                    data[i][2] = sanPham.getSoLuong();
                    data[i][3] = sanPham.getGia();
                }

                JTable table = new JTable(data, columnNames);
                
                DefaultTableCellRenderer rightAlignRenderer = new DefaultTableCellRenderer();
                rightAlignRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
                table.getColumnModel().getColumn(2).setCellRenderer(rightAlignRenderer);
                table.getColumnModel().getColumn(3).setCellRenderer(rightAlignRenderer);

                JTableHeader header = table.getTableHeader();
                header.setBackground(Color.LIGHT_GRAY);
                header.setForeground(Color.BLUE);
                header.setFont(new Font("Arial", Font.BOLD, 14));

                table.setShowGrid(true);
                table.setGridColor(Color.BLACK);
                table.setRowHeight(25);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JDialog dialog = new JDialog(frame, "Danh sách Sản Phẩm", true);
                dialog.add(scrollPane);
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });

    }
    
}