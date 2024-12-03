import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {
    private final Player player;
    private final Runnable refreshUI;

    public Admin(Player player, Runnable refreshUI) {
        this.player = player;
        this.refreshUI = refreshUI;

        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        getContentPane().setBackground(Color.BLACK);

        // Number of Bees Input
        JLabel beesLabel = new JLabel("Set Number of Bees:", SwingConstants.CENTER);
        beesLabel.setForeground(Color.YELLOW);
        JTextField beesField = new JTextField();
        beesField.setBackground(Color.DARK_GRAY);
        beesField.setForeground(Color.YELLOW);
        add(beesLabel);
        add(beesField);

        // Amount of Honey Input
        JLabel honeyLabel = new JLabel("Set Amount of Honey:", SwingConstants.CENTER);
        honeyLabel.setForeground(Color.YELLOW);
        JTextField honeyField = new JTextField();
        honeyField.setBackground(Color.DARK_GRAY);
        honeyField.setForeground(Color.YELLOW);
        add(honeyLabel);
        add(honeyField);

        // Apply Button
        JButton applyButton = new JButton("Apply Changes");
        applyButton.setBackground(Color.DARK_GRAY);
        applyButton.setForeground(Color.YELLOW);
        applyButton.addActionListener(e -> {
            try {
                // Set Number of Bees
                int numBees = Integer.parseInt(beesField.getText());
                while (player.getOwnedBees().size() < numBees) {
                    player.getOwnedBees().add(new Bee("Drone Bee", 1.0, true, BeeType.WORKER));
                }
                while (player.getOwnedBees().size() > numBees) {
                    player.getOwnedBees().remove(player.getOwnedBees().size() - 1);
                }

                // Set Amount of Honey
                double honey = Double.parseDouble(honeyField.getText());
                player.setHoney(honey);

                // Refresh UI
                refreshUI.run();

                JOptionPane.showMessageDialog(this, "Changes applied successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
            }
        });
        add(new JLabel()); // Empty placeholder for grid layout
        add(applyButton);

        setVisible(true);
    }
}
