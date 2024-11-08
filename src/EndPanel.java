import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class EndPanel extends JPanel implements MouseListener {
    private BufferedImage endPanelBackground;
    private HashMap<Integer, Integer> playerData;
    private int winner;

    public EndPanel(HashMap<Integer, Integer> playerData, int winner) {
        this.playerData = playerData;
        this.winner = winner;
        try {
            endPanelBackground = ImageIO.read(EndPanel.class.getResource(""));
        }
        catch (Exception e) {
            System.out.println("EndPanel resource not found");
        }
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
