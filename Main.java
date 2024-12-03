public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Player player = new Player("Player 1"); // Create Player instance

        // Pass both GameController and Player to the UI
        new MainGameUI(gameController);

        // Start the game
        gameController.startGame();
    }
}
