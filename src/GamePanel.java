import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.awt.Color;
import java.awt.Graphics;
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
    int winner;

    public GamePanel() {
        try {
            title = ImageIO.read(GamePanel.class.getResource("Image/SplendorTitle.png"));
        } catch (Exception e) {
            System.out.println("Resource Location Failure");
        }
        addMouseListener(this);


        //Game initialization occurs here

        players = new Player[4];
        for(int i = 0; i < 4; i++)
            players[i] = new Player(false);
        players[0].setP1(true);

    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.GAME_BACKGROUND, 0, 0, getWidth(), getHeight(), null);

        g.setColor(Color.BLACK);
        g.fillRect(0, getWidth()/8-10, getWidth()/2, 4);
        g.fillRect(getWidth()/2-2, 0, 4, 520);
        g.fillRect(0, 520, getWidth(), 4);
        g.fillRect(635, 268, 1280, 4);
        g.fillRect(958, 0, 4, 520);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g.drawString("Nobles", 40, 80);
        g.drawString("Player 1: " + players[0].getScore(), 640, 40);
        g.drawString("Player 2: " + players[1].getScore(), 970, 40);
        g.drawString("Player 3: " + players[2].getScore(), 640, 310);
        g.drawString("Player 4: " + players[3].getScore(), 970, 310);

        HashMap<Type, Integer> tester = new HashMap<>();
        tester.put(Type.RED, 1);
        tester.put(Type.BLUE, 2);
        tester.put(Type.GREEN, 3);
        tester.put(Type.BLACK, 4);
        tester.put(Type.WHITE, 5);
        Card c = new Card(1, Type.WILD, 0, tester);
        c.draw(g, 200, 200, 100);
    }

    Patron[] patrons = new Patron[5];

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

    // a helper function that starts the game when the startgame or new game button is pressed
    public void startGame(int playerNumber) { //just set playerNumber to 4 if we do not want customizable
        for (int i = 0; i < playerNumber; i++) {
            players[i] = new Player(false);
        }
        players[0].setP1(true);

        ArrayList<Card> temp = new ArrayList<>();
        Scanner scan = new Scanner("Image/cards.txt");

        //turn text into cards
         // make sure to initialize deck before the loop
        for (int i = 0; i<3; i++ ) {
            decks[i] = new Deck(new ArrayList<>());
        }
        for (int i = 0; i < 90; i++) {//set this to 90 when the cards.txt file is complete
            int points = Integer.parseInt(scan.nextLine());
            Type discountcolor = Type.valueOf(scan.nextLine());
            int tier = Integer.parseInt(scan.nextLine());


            HashMap<Type, Integer> prices = new HashMap<>();
            prices.put(Type.BLACK, Integer.parseInt(scan.nextLine()));
            prices.put(Type.WHITE, Integer.parseInt(scan.nextLine()));
            prices.put(Type.RED, Integer.parseInt(scan.nextLine()));
            prices.put(Type.BLUE, Integer.parseInt(scan.nextLine()));
            prices.put(Type.GREEN, Integer.parseInt(scan.nextLine()));
            Card aCard = new Card(points, discountcolor, tier, prices);
            scan.nextLine();

            decks[aCard.getTier()].addCard(aCard);
        }
        //shuffle the decks
        for (int i = 0; i < 3; i++) {
            decks[i].shuffle();
        }

        int tracker = 0;

        //not sure if this will work, only time will tell.
        //adds patrons to the patron stack after clearing out said stack
        patrons = new Patron[5];
        while (patrons[4] != null) {
            Patron p = Patron.patronList[(int) (Math.random() * 10)];
            for (int i = 0; i <= tracker; i++) {
                if (i == tracker) {
                    patrons[tracker] = p;
                    tracker++;
                    break;
                }
                if (patrons[i].equals(p)) {
                    break;
                }

            }
        }







    }

}
