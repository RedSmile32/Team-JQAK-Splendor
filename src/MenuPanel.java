import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MenuPanel extends JPanel implements MouseListener {
    private int easterEggTrigger;


    public MenuPanel() {
        easterEggTrigger = 0;
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.MENU_BACKGROUND, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.drawString("Click anywhere to start", getWidth()/2 - 10, 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Splendor.startGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
