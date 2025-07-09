package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame frame;

    public MainView() {
        frame = new JFrame("Quản lý Kho Hàng");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton sanPhamButton = new JButton("Quản lý Sản Phẩm");
        sanPhamButton.setBounds(50, 50, 200, 25);
        panel.add(sanPhamButton);

        JButton phieuNhapButton = new JButton("Quản lý Phiếu Nhập");
        phieuNhapButton.setBounds(50, 100, 200, 25);
        panel.add(phieuNhapButton);

        JButton phieuXuatButton = new JButton("Quản lý Phiếu Xuất");
        phieuXuatButton.setBounds(50, 150, 200, 25);
        panel.add(phieuXuatButton);

        sanPhamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SanPhamView();
            }
        });

        phieuNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PhieuNhapView();
            }
        });

        phieuXuatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PhieuXuatView();
            }
        });
    }

    public static void main(String[] args) {
        new MainView();
    }
}