import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

public class GameFrame extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    Dimension size;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;

    public GameFrame(String framename) {
        super(framename);
        size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(1);
        endPanel = new EndPanel(null);
        add(menuPanel);
        setVisible(true);
    }

    /**
     * This method removes the MenuPanel component and initializes and adds a GamePanel component
     * to the JFrame. This method is called by the runner class, which is where some of the
     * communication between classes happens.
     */
    public void startGame() {
        remove(menuPanel);
        remove(gamePanel);
        remove(endPanel);
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }

    public void endGame(TreeSet<Player> playerTreeSet) {
        remove(menuPanel);
        remove(gamePanel);
        remove(endPanel);
        endPanel = new EndPanel(playerTreeSet);
        add(endPanel);
        setVisible(true);
    }

    public void mainMenu() {
        remove(menuPanel);
        remove(gamePanel);
        remove(endPanel);
        menuPanel = new MenuPanel();
        add(menuPanel);
        setVisible(true);
    }
}
