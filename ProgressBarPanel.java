import javax.swing.*;
import java.awt.*;

public class ProgressBarPanel extends JPanel {
    private JProgressBar progressBar;

    public ProgressBarPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setForeground(Color.YELLOW);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 14));
        add(progressBar, BorderLayout.CENTER);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void resetProgress() {
        progressBar.setValue(0);
    }
}
