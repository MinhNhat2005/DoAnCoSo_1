package view;
import javax.swing.*;
import java.awt.*;

public class ChatServerWindow  extends JFrame {
	 private JTextArea chatArea;
	    private JTextField messageField;
	    private JButton sendButton;

	    public ChatServerWindow() {
	        setTitle("Chat - Server");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 300);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());

	        chatArea = new JTextArea();
	        chatArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(chatArea);
	        panel.add(scrollPane, BorderLayout.CENTER);

	        JPanel bottomPanel = new JPanel();
	        bottomPanel.setLayout(new BorderLayout());

	        messageField = new JTextField();
	        bottomPanel.add(messageField, BorderLayout.CENTER);

	        sendButton = new JButton("Send");
	        sendButton.addActionListener(e -> sendMessage());
	        bottomPanel.add(sendButton, BorderLayout.EAST);

	        panel.add(bottomPanel, BorderLayout.SOUTH);

	        add(panel);
	    }

	    private void sendMessage() {
	        String message = messageField.getText().trim();
	        if (!message.isEmpty()) {
	            // Gửi tin nhắn tới tất cả các client
	            // Điều này cần được thực hiện thông qua kết nối server
	            // Ví dụ: server.sendToAll(message);
	            chatArea.append("You: " + message + "\n");
	            messageField.setText("");
	        }
	    }

	    // Phương thức để hiển thị tin nhắn từ các client
	    public void receiveMessage(String clientName, String message) {
	        chatArea.append(clientName + ": " + message + "\n");
	    }
}
