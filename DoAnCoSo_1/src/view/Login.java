package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import java.awt.Cursor;

public class Login extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	    public Login() {
	    	getContentPane().setPreferredSize(new Dimension(400, 400));
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(800, 429);
	        setTitle("Đăng nhập");
	        setLocationRelativeTo(null); // Đặt cửa sổ vào giữa màn hình
	        
	        JPanel panel = new JPanel();
	        panel.setPreferredSize(new Dimension(400, 400));
	        getContentPane().add(panel, BorderLayout.CENTER);
	        panel.setLayout(null);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setBackground(Color.GREEN);
	        panel_1.setForeground(Color.DARK_GRAY);
	        panel_1.setPreferredSize(new Dimension(400, 400));
	        panel_1.setBounds(0, 0, 400, 391);
	        panel.add(panel_1);
	        panel_1.setLayout(null);
	       
	     // Load hình ảnh
	        ImageIcon imageIcon = new ImageIcon(Login.class.getResource("vku.png"));
	        Image image = imageIcon.getImage();

	        // Tính toán kích thước mới của hình ảnh
	        int width = image.getWidth(panel_1);
	        int height = image.getHeight(panel_1);

	        int newWidth = width;
	        int newHeight = height;

	        // Nếu chiều rộng hoặc chiều cao của hình ảnh lớn hơn kích thước của panel_1,
	        // ta thu nhỏ hình ảnh cho vừa khít với kích thước của panel_1
	        if (width > panel_1.getWidth() || height > panel_1.getHeight()) {
	            double widthRatio = (double) panel_1.getWidth() / width;
	            double heightRatio = (double) panel_1.getHeight() / height;
	            double scale = Math.min(widthRatio, heightRatio);
	            
	            newWidth = (int) (width * scale);
	            newHeight = (int) (height * scale);
	        }

	        // Tạo một JLabel để chứa hình ảnh với kích thước mới
	        JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));

	        // Tính toán vị trí để hình ảnh nằm giữa panel_1
	        int x = (panel_1.getWidth() - newWidth) / 2;
	        int y = (panel_1.getHeight() - newHeight) / 2;

	        // Đặt vị trí và kích thước cho label
	        label.setBounds(x, y, newWidth, newHeight);

	        // Thêm label vào panel_1
	        panel_1.add(label);

	        // Thêm panel_1 vào panel hoặc frame (phụ thuộc vào nơi bạn đang sử dụng nó)
	        panel.add(panel_1);
	      

	        
	        JLabel lblNewLabel = new JLabel("LOGIN");
	        lblNewLabel.setToolTipText("");
	        lblNewLabel.setBackground(Color.DARK_GRAY);
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
	        lblNewLabel.setForeground(Color.BLUE);
	        lblNewLabel.setBounds(540, 38, 126, 43);
	        panel.add(lblNewLabel);
	        
	        JLabel lblUsername = new JLabel("UserName:");
	        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
	        lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
	        lblUsername.setBounds(410, 127, 126, 43);
	        panel.add(lblUsername);
	        
	        textField = new JTextField();
	        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        textField.setBounds(540, 127, 207, 45);
	        panel.add(textField);
	        textField.setColumns(10);
	        
	        JLabel lblPassword = new JLabel("Password:");
	        lblPassword.setForeground(new Color(0, 0, 0));
	        lblPassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
	        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
	        lblPassword.setBounds(416, 209, 126, 43);
	        panel.add(lblPassword);
	        
	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        passwordField.setBounds(540, 210, 207, 43);
	        panel.add(passwordField);
	        
	        JButton btnNewButton = new JButton("Login");
	        btnNewButton.setBackground(Color.GREEN);
	        btnNewButton.setForeground(Color.DARK_GRAY);
	        btnNewButton.setFont(new Font("Thuathien", Font.BOLD, 20));
	        btnNewButton.setBounds(430, 298, 151, 43);
	        panel.add(btnNewButton);
	        
	        JButton btnCancel = new JButton("Cancel");
	        btnCancel.setForeground(Color.DARK_GRAY);
	        btnCancel.setFont(new Font("Thuathien", Font.BOLD, 20));
	        btnCancel.setBackground(Color.RED);
	        btnCancel.setBounds(613, 298, 151, 43);
	        panel.add(btnCancel);
	     // Xử lý sự kiện click vào nút "Cancel"
	        btnCancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Thoát chương trình khi nhấn vào nút Cancel
	                System.exit(0);
	            }
	        });

	        btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String username = textField.getText();
					String password = new String(passwordField.getPassword());
					//kiểm tra tên đăng nhập và mật khẩu
					if(username.equals("phamminhnhat") && password.equals("2005")) {
						  // Nếu đúng, đóng cửa sổ đăng nhập và mở cửa sổ Quản lý Ký túc xá
	                    dispose(); // Đóng cửa sổ đăng nhập
	                    openQLKTXWindow(); // Mở cửa sổ Quản lý Ký túc xá
	                } else {
	                    JOptionPane.showMessageDialog(Login.this, "Tên đăng nhập hoặc mật khẩu không đúng!");
	                }
					
					
				}
			});
	    }
	    
	   
	    private void openQLKTXWindow() {
	        QLKTXview qlktxView = new QLKTXview();
	        qlktxView.setVisible(true);
	    }
}
