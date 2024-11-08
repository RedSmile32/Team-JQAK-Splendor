import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    final int WIDTH = 1280;
    final int HEIGHT = 720;
    Dimension size;
    private MenuPanel menuPanel;

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

    }
}
