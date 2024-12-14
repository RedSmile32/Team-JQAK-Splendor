import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.*;

public class EndPanel extends JPanel implements MouseListener {
    private HashMap<Integer, Integer> playerData;
    private int winner;

    public EndPanel(TreeSet<Player> playerTreeSet) {
        if(playerTreeSet!=null) {
            this.winner = playerTreeSet.getFirst().num;
            playerData = new HashMap<>();
            Iterator<Player> iter = playerTreeSet.iterator();
            while (iter.hasNext()) {
                Player p = iter.next();
                playerData.put(p.num, p.getScore());
            }
            addMouseListener(this);
//        try {
//            endPanelBackground = ImageIO.read(EndPanel.class.getResource("Image/EndPanelBackground.png"));
//        }
//        catch (Exception e) {
//            System.out.println("EndPanel resource not found");
//        }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.endBackground(), 0, 0, getWidth() * 6/5, getHeight(), null);
//        g.setColor(Color.WHITE);
//        g.drawString("End screen", getWidth()/2 - 10, 20);


        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.setColor(Color.BLACK);
        g.drawString("Leaderboard",905,95);
        g.setFont(new Font("Arial", Font.PLAIN, 50));
        g.drawString("Main", 1020,540);
        g.drawString("menu", 1010,580);
        g.setFont(new Font("Arial", Font.PLAIN, 70));
        g.drawString("Play Again", 275, 550);
        g.setFont(new Font("Arial", Font.PLAIN, 80));
        g.drawString("Player " + winner + " won", 170, 115);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Total points: " + playerData.get(winner),290,195);

        Set<Integer> playerList = playerData.keySet();
        Iterator<Integer> iter = playerList.iterator();

        //Leaderboard draw
        int extraY = 0;
        while (iter.hasNext()) {
            Integer current = iter.next();
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Player " + (current + 1),960,155 + extraY);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Points: " + playerData.get(current),980,180 + extraY);
            extraY += 60;
//            System.out.print();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + " " + y);
        if (x >= 38 && x <= 847 && y >= 437 && y <= 660) {
            System.out.print("Play Again");
            removeMouseListener(this);
            Splendor.startGame();
        } else if (x >= 883 && x <= 1258 && y >= 436 && y <= 662) {
            System.out.print("Main menu");
            removeMouseListener(this);
            Splendor.mainMenu();
        }
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

