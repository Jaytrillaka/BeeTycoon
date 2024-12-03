import javax.swing.*;
import java.awt.*;

public class ShopUI extends JFrame {
    private final Player player;
    private final Runnable refreshUI;

    public ShopUI(Player player, Runnable refreshUI) {
        this.player = player;
        this.refreshUI = refreshUI;

        setTitle("Shop");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1)); // Updated to 4 rows
        getContentPane().setBackground(Color.BLACK);

        JLabel shopLabel = new JLabel("Welcome to the Shop!", SwingConstants.CENTER);
        shopLabel.setFont(new Font("Arial", Font.BOLD, 18));
        shopLabel.setForeground(Color.YELLOW);
        add(shopLabel);

        JButton buyBeeButton = new JButton("Buy a Drone Bee (10 Honey)");
        buyBeeButton.setBackground(Color.DARK_GRAY);
        buyBeeButton.setForeground(Color.YELLOW);
        buyBeeButton.addActionListener(e -> {
            if (player.getHoney() >= 10) {
                player.getOwnedBees().add(new Bee("Drone Bee", 1.0, true, BeeType.WORKER));
                player.setHoney(player.getHoney() - 10);
                JOptionPane.showMessageDialog(this, "Drone Bee purchased successfully!");
                refreshUI.run(); // Refresh the main UI
            } else {
                JOptionPane.showMessageDialog(this, "Not enough honey to buy a bee.");
            }
        });
        add(buyBeeButton);

        JButton rewardsButton = new JButton("Rewards");
        rewardsButton.setBackground(Color.DARK_GRAY);
        rewardsButton.setForeground(Color.YELLOW);
        rewardsButton.setFont(new Font("Arial", Font.BOLD, 14));
        rewardsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Rewards are coming soon!");
        });
        add(rewardsButton);

        JButton closeShopButton = new JButton("Close Shop");
        closeShopButton.setBackground(Color.DARK_GRAY);
        closeShopButton.setForeground(Color.YELLOW);
        closeShopButton.addActionListener(e -> dispose());
        add(closeShopButton);

        setVisible(true);
    }
}
