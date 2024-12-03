import javax.swing.*;
import java.awt.*;

public class ResourcePanel extends JPanel {
    private JLabel nectarLabel;
    private JLabel honeyLabel;
    private Player player;

    public ResourcePanel(Player player) {
        this.player = player;
        setLayout(new GridLayout(1, 2));
        setBackground(Color.BLACK);

        nectarLabel = createLabel("Nectar: " + (int) player.getNectar());
        honeyLabel = createLabel("Honey: " + (int) player.getHoney());
        add(nectarLabel);
        add(honeyLabel);
    }

    public void updateResources() {
        nectarLabel.setText("Nectar: " + (int) player.getNectar());
        honeyLabel.setText("Honey: " + (int) player.getHoney());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
}
