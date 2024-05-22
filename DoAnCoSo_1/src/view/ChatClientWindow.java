package view;
import javax.swing.*;
import java.awt.*;

public class ChatClientWindow extends JFrame{
	  private JTextArea chatArea;
	    private JTextField messageField;
	    private JButton sendButton;
	    private String clientName;

	    public ChatClientWindow(String clientName) {
	        this.clientName = clientName;

	        setTitle("Chat - Client: " + clientName);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	            // Gửi tin nhắn tới máy chủ hoặc các máy khách khác tùy theo nhu cầu
	            chatArea.append("You: " + message + "\n");
	            messageField.setText("");
	        }
	    }

	    // Phương thức để hiển thị tin nhắn từ máy chủ hoặc các máy khách khác
	    public void receiveMessage(String message) {
	        chatArea.append("Server: " + message + "\n");
	    }

}
