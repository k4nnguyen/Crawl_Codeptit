import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login PTIT");
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");

        JTextField userText = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();

        JButton runButton = new JButton("RUN");
        JLabel resultLabel = new JLabel("");

        frame.add(userLabel);
        frame.add(userText);
        frame.add(passLabel);
        frame.add(passText);
        frame.add(resultLabel);
        frame.add(runButton);

        runButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            try {
                ProcessBuilder pb = new ProcessBuilder(
                    "python", "../crawl.py", username, password
                );
                // Ép Python output ra UTF-8
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
