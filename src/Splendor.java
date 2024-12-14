import java.util.TreeSet;

public class Splendor {

    private static GameFrame splendor;

    public static void main(String[] args) {
        splendor = new GameFrame("Splendor");
    }

    public static void startGame() {
        splendor.startGame();
    }

    public static void endGame(TreeSet<Player> playerTreeSet) {
        splendor.endGame(playerTreeSet);
    }

    public static void mainMenu() {
        //Make new menu screen
        splendor.mainMenu();
    }
}
