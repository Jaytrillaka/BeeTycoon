import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String name;
    private double nectar;
    private double honey;
    private List<Bee> ownedBees;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.nectar = 0;
        this.honey = 0;
        this.ownedBees = new ArrayList<>();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNectar() {
        return nectar;
    }

    public void setNectar(double nectar) {
        this.nectar = nectar;
    }

    public double getHoney() {
        return honey;
    }

    public void setHoney(double honey) {
        this.honey = honey;
    }

    public List<Bee> getOwnedBees() {
        return ownedBees;
    }

    public void addBee(Bee bee) {
        this.ownedBees.add(bee);
    }

    public void removeBee(Bee bee) {
        this.ownedBees.remove(bee);
    }

    // Nectar Collection Logic
    public void collectNectar() {
        for (Bee bee : ownedBees) {
            if (bee.canCollectNectar()) { // Ensure the bee can collect nectar
                this.nectar += bee.getNectarRate(); // Add the nectar collected by the bee
            }
        }
    }

    // Auto Nectar Collection Logic (for progress bar or automatic actions)
    public void collectNectarAutomatically() {
        collectNectar(); // Use the same method
    }

    // Convert Nectar to Honey
    public void convertNectarToHoney() {
        if (nectar >= 100) {
            honey += nectar / 100; // Example: Every 100 nectar converts to 1 honey
            nectar = 0;
        }
    }

    // Override toString for debugging and logging
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", nectar=" + nectar +
                ", honey=" + honey +
                ", ownedBees=" + ownedBees +
                '}';
    }
}
