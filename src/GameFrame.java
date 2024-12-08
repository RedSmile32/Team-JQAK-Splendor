import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    Dimension size;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    public GameFrame(String framename) {
        super(framename);
        size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel = new MenuPanel();
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
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }
}
