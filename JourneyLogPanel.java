import javax.swing.*;
import java.awt.*;

public class JourneyLogPanel extends JPanel {
    private JTextArea logArea;

    public JourneyLogPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        logArea = new JTextArea("Journey Log: \n", 5, 20);
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.YELLOW);
        logArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return logArea;
    }

    public void addLog(String log) {
        logArea.append(log + "\n");
    }
}
