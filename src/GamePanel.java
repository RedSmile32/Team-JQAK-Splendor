import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class GamePanel extends JPanel implements MouseListener {
    Player[] players = new Player[4];
    Deck[] decks = new Deck[3];
    HashMap<Integer, Card> displayedCards;
    HashMap<Type, Integer> gameTokens;
    BufferedImage title;
    ArrayList<Patron> gamePatrons;
    int activePlayer;
    int turnState;

    public GamePanel() {
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.gameBackground(), 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.drawString("gamePanel is a go!", 10, 20);
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
