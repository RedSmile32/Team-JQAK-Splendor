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
        menuPanel = new MenuPanel();
        add(menuPanel);
        setVisible(true);
    }

    public void startGame() {
        remove(menuPanel);
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }
}
