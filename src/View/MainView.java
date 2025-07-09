package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

    public MainView() {
        setTitle("Quản lý Kho Hàng");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
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
        // Nếu bạn chạy riêng MainView
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
}
