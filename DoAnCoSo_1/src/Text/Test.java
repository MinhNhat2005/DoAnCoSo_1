package Text;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import view.Login;
import view.QLKTXview;

public class Test {
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    // Khởi tạo và hiển thị cửa sổ đăng nhập
	                    Login frame = new Login();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

}