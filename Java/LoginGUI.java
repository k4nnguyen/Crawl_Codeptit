import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login PTIT");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3)); // thêm 1 cột để chứa icon 👁

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();

        // Nút hiện/ẩn mật khẩu
        JButton toggleButton = new JButton("Hiện mật khẩu");
        toggleButton.setFocusable(false);

        // Lưu lại ký tự echo mặc định
        char defaultEcho = passText.getEchoChar();

        toggleButton.addActionListener(e -> {
            if (passText.getEchoChar() == (char) 0) {
                // đang hiện -> chuyển về ẩn
                passText.setEchoChar(defaultEcho);
            } else {
                // đang ẩn -> chuyển sang hiện
                passText.setEchoChar((char) 0);
            }
        });

        JButton runButton = new JButton("RUN");
        JLabel resultLabel = new JLabel("");

        frame.add(userLabel);
        frame.add(userText);
        frame.add(new JLabel("")); // chừa ô trống
        frame.add(passLabel);
        frame.add(passText);
        frame.add(toggleButton);   
        frame.add(resultLabel);
        frame.add(runButton);
        frame.add(new JLabel("")); // cân layout

        runButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            try {
                ProcessBuilder pb = new ProcessBuilder(
                        "python", "../crawl.py", username, password
                );
                pb.environment().put("PYTHONIOENCODING", "utf-8");
                pb.redirectErrorStream(true);
                Process process = pb.start();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), "UTF-8")
                );
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                resultLabel.setText("<html>" + output.toString().replace("\n", "<br>") + "</html>");
            } catch (Exception ex) {
                resultLabel.setText("Lỗi: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
