import java.io.Serializable;

public class Bee implements Serializable {
    private String name;
    private double nectarRate;
    private boolean canGoOnJourney;
    private BeeType type;
    private int cooldown;

    // Constructor
    public Bee(String name, double nectarRate, boolean canGoOnJourney, BeeType type) {
        this.name = name;
        this.nectarRate = nectarRate;
        this.canGoOnJourney = canGoOnJourney;
        this.type = type;
        this.cooldown = 0;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNectarRate() {
        return nectarRate;
    }

    public void setNectarRate(double nectarRate) {
        this.nectarRate = nectarRate;
    }

    public boolean canGoOnJourney() {
        return canGoOnJourney && cooldown == 0;
    }

    public void setCanGoOnJourney(boolean canGoOnJourney) {
        this.canGoOnJourney = canGoOnJourney;
    }

    public BeeType getType() {
        return type;
    }

    public void setType(BeeType type) {
        this.type = type;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void startJourney() {
        if (canGoOnJourney()) {
            canGoOnJourney = false;
            cooldown = 10; // Example cooldown period of 10 seconds
        }
    }

    public void decrementCooldown() {
        if (cooldown > 0) {
            cooldown--;
            if (cooldown == 0) {
                canGoOnJourney = true; // Reset journey readiness
            }
        }
    }

    // New canCollectNectar method
    public boolean canCollectNectar() {
        return nectarRate > 0; // Example logic: A bee can collect nectar if its nectar rate > 0
    }

    @Override
    public String toString() {
        return "Bee{" +
                "name='" + name + '\'' +
                ", nectarRate=" + nectarRate +
                ", canGoOnJourney=" + canGoOnJourney +
                ", type=" + type +
                ", cooldown=" + cooldown +
                '}';
    }

}
