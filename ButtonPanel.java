import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ButtonPanel extends JPanel {
    public ButtonPanel(Player player, Runnable refreshUI, Consumer<String> logAction, Runnable openShop) {
        setLayout(new GridLayout(1, 6)); // Updated grid for 6 buttons
        setBackground(Color.BLACK);

        // Collect Nectar Button
        JButton collectNectarButton = createButton("Collect Nectar", e -> {
            player.collectNectar();
            refreshUI.run();
            logAction.accept("Collected nectar.");
        });

        // Produce Honey Button
        JButton produceHoneyButton = createButton("Produce Honey", e -> {
            if (player.getNectar() >= 100) {
                player.convertNectarToHoney();
                refreshUI.run();
                logAction.accept("Converted nectar to honey.");
            } else {
                JOptionPane.showMessageDialog(this, "Not enough nectar to produce honey.");
            }
        });

        // Send Bee on Journey Button
        JButton sendBeeButton = createButton("Send Bee on Journey", e -> {
            String journeyResult = JourneyManager.sendBeesOnJourney(player, 1); // Sends 1 bee
            refreshUI.run();
            logAction.accept(journeyResult);
        });

        // Shop Button
        JButton shopButton = createButton("Shop", e -> openShop.run());





        // Admin Panel Button
        JButton adminButton = createButton("Admin", e -> new Admin(player, refreshUI));

        // Add Buttons to Panel
        add(collectNectarButton);
        add(produceHoneyButton);
        add(sendBeeButton);
        add(shopButton);
        add(adminButton);
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
}
