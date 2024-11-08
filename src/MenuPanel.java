import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MenuPanel extends JPanel implements MouseListener, KeyListener{
    BufferedImage titleBackground;
    BufferedImage rulesBackground;
    BufferedImage rulesForeground;
    BufferedImage startButton;
    BufferedImage rulesButton;

    public MenuPanel() {
        try {
            titleBackground = ImageIO.read(MenuPanel.class.getResource(""));
            System.out.println("Title background found");
            rulesBackground = ImageIO.read(MenuPanel.class.getResource(""));
            System.out.println("Rules background found");
            rulesForeground = ImageIO.read(MenuPanel.class.getResource(""));
            System.out.println("Rules foreground found");
            startButton = ImageIO.read(MenuPanel.class.getResource(""));
            System.out.println("Start button found");
            rulesButton = ImageIO.read(MenuPanel.class.getResource(""));
            System.out.println("Rules button found");
        }
        catch(Exception e) {
            System.out.println("Image not found");
        }
        addMouseListener(this);
        addKeyListener(this);
    }

    public void paint(Graphics g) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
