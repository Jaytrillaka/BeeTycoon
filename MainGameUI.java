import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class MainGameUI extends JFrame {
    private Player player;
    private ResourcePanel resourcePanel;
    private BeeStatsPanel beeStatsPanel;
    private JourneyLogPanel journeyLogPanel;
    private ProgressBarPanel progressBarPanel;
    private Timer progressTimer;

    public MainGameUI(GameController gameController) {
        // Prompt for player name if not loaded from save
        this.player = loadGame();
        if (player == null || player.getName() == null || player.getName().isEmpty()) {
            String playerName = JOptionPane.showInputDialog(this, "Enter your Queen Bee name:", "Welcome to Bee Tycoon!", JOptionPane.QUESTION_MESSAGE);
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Queen Bee"; // Default name if nothing is entered
            }
            player = new Player(playerName.trim());
        }

        setTitle("Bee Tycoon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        // Add Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel headerLabel = new JLabel("Queen Bee (" + player.getName() + ")");
        headerLabel.setForeground(Color.YELLOW);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Add Resource Panel
        resourcePanel = new ResourcePanel(player);
        add(resourcePanel, BorderLayout.NORTH);

        // Center Panel for Bee Stats and Progress Bar
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);

        progressBarPanel = new ProgressBarPanel();
        centerPanel.add(progressBarPanel, BorderLayout.NORTH);

        beeStatsPanel = new BeeStatsPanel(player);
        centerPanel.add(beeStatsPanel, BorderLayout.CENTER);

        journeyLogPanel = new JourneyLogPanel();
        centerPanel.add(new JScrollPane(journeyLogPanel.getTextArea()), BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Add Buttons
        ButtonPanel buttonPanel = new ButtonPanel(player, this::refreshUI, journeyLogPanel::addLog, this::openShopUI);
        add(buttonPanel, BorderLayout.SOUTH);

        // Auto-save on exit
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveGame();
                super.windowClosing(e);
            }
        });

        // Timer for Progress Bar
        progressTimer = new Timer(200, e -> updateProgressBar());
        progressTimer.start();

        setVisible(true);
    }

    private void updateProgressBar() {
        JProgressBar progressBar = progressBarPanel.getProgressBar();
        int currentValue = progressBar.getValue();

        if (currentValue < 100) {
            progressBar.setValue(currentValue + 5); // Increment progress bar
        } else {
            progressBarPanel.resetProgress(); // Reset progress bar
            player.collectNectarAutomatically(); // Collect nectar when full
            refreshUI(); // Refresh UI after collecting nectar
            journeyLogPanel.addLog("Nectar collected automatically.");
        }
    }

    private void refreshUI() {
        resourcePanel.updateResources(); // Update nectar and honey labels
        beeStatsPanel.refresh(); // Refresh bee stats table
    }

    private void openShopUI() {
        new ShopUI(player, this::refreshUI); // Pass refresh method to shop UI
    }

    private void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game_save"))) {
            out.writeObject(player);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    private Player loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game_save.dat"))) {
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No save file found, starting new game.");
            return null; // Indicate no saved game found
        }
    }
}
