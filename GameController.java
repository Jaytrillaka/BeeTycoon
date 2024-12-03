public class GameController {
    private boolean isGameRunning;

    public void startGame() {
        isGameRunning = true;
    }

    public void stopGame() {
        isGameRunning = false;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }
}
