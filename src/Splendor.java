import java.util.HashMap;

public class Splendor {

    private static GameFrame splendor;

    public static void main(String[] args) {
        splendor = new GameFrame("Splendor");
    }

    public static void startGame() {
        splendor.startGame();
    }

    public static void endGame(HashMap<Integer, Integer> plrData, int winner) {
        splendor.endGame(plrData, winner);
    }

    public static void mainMenu() {
        //Make new menu screen
        splendor.mainMenu();
    }
}
