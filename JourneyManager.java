import java.util.List;
import java.util.stream.Collectors;

public class JourneyManager {

    public static String sendBeesOnJourney(Player player, int numBees) {
        List<Bee> availableBees = player.getOwnedBees().stream()
                .filter(Bee::canGoOnJourney)
                .limit(numBees)
                .collect(Collectors.toList());

        if (availableBees.isEmpty()) {
            return "No available bees to send on a journey!";
        }

        for (Bee bee : availableBees) {
            bee.startJourney(); // Mark bee as on a journey and set cooldown
        }

        int totalNectarCollected = 50 * availableBees.size(); // Example nectar value per bee
        player.setNectar(player.getNectar() + totalNectarCollected);

        return "Sent " + availableBees.size() + " bee(s) on a journey. Collected " + totalNectarCollected + " nectar!";
    }

    public static void updateBeeCooldowns(Player player) {
        player.getOwnedBees().forEach(Bee::decrementCooldown); // Decrement cooldown for all bees
    }
}
