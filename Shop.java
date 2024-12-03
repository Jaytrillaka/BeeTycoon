import javax.swing.*;

public class Shop {
    private Player player;

    public Shop(Player player) {
        this.player = player;
    }

    public boolean buyBee(String type) {
        if (player.getHoney() >= 10) {
            player.getOwnedBees().add(new Bee("Purchased Bee", 1.0, true, BeeType.valueOf(type.toUpperCase())));
            player.setHoney(player.getHoney() - 10); // Deduct honey
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Not enough honey to buy a bee.");
            return false;
        }
    }
}
