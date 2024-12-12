import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Array;
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
        startGame(4);

    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.GAME_BACKGROUND, 0, 0, getWidth(), getHeight(), null);

        g.setColor(Color.BLACK);
        g.fillRect(0, 150, getWidth()/2, 4);
        g.fillRect(631, 0, 4, getHeight());
        g.fillRect(635, 520, 1280, 4);
        g.fillRect(635, 268, 1280, 4);
        g.fillRect(958, 0, 4, 520);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g.drawString("Nobles", 40, 80);
        g.drawString("Player 1: " + players[0].getScore(), 640, 40);
        g.drawString("Player 2: " + players[1].getScore(), 970, 40);
        g.drawString("Player 3: " + players[2].getScore(), 640, 310);
        g.drawString("Player 4: " + players[3].getScore(), 970, 310);

        for(int i = 0; i < 4; i++) {
            int numTimes = 0;

            Player current = players[i];
            int numCardsDrawn = 0;
            for (Type t : Type.values()) {
                g.drawImage(ImageHandler.getTokenImage(t), 640 + (327 * (i % 2)) + 30 * numTimes, 230 + (250 * (i / 2)), 30, 30, null);
                numTimes++;
                g.setFont(new Font("SansSerif", Font.PLAIN, 18));
                g.setColor(Color.BLACK);
                g.drawString(players[i].tokens.get(t) + "", 620 + (327 * (i % 2)) + 30 * numTimes, 225 + (250 * (i / 2)));

                if(!t.equals(Type.WILD)) {
                    ArrayList<Card> c = current.cards.get(t);
                    Card card;
                    if (c.size() > 0) {
                        card = c.getFirst();
                        card.draw(g, 640 + (50 * numCardsDrawn) + (325 * (i % 2)), 100 + 250 * (i / 2), 30);
                        g.drawString(c.size() + "x", 650 + (50 * numCardsDrawn) + (325 * (i % 2)), 95 + 250 * (i / 2));
                    } else {
                        g.fillRect(640 + (50 * numCardsDrawn) + (325 * (i % 2)), 100 + 250 * (i / 2), 45, 60);
                        g.drawImage(ImageHandler.getTokenImage(t), 640 + (50 * numCardsDrawn) + (325 * (i % 2)), 108 + 250 * (i / 2), 45, 45, null);
                        g.drawString("0x", 650 + (50 * numCardsDrawn) + (325 * (i % 2)), 95 + 250 * (i / 2));
                    }
                    numCardsDrawn++;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                decks[i].cardList.get(j).draw(g, 100 * j + 200, 130 * i + 230, 60);

            }
        }

        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        for(int i = 0; i < Type.values().length; i++) {
            g.drawImage(ImageHandler.getTokenImage(Type.values()[i]), 640+(70 * i), 540, 70, 70, null);
            g.drawString(gameTokens.get(Type.values()[i])+"", 665+(70 * i), 640);
        }
    }

    Patron[] patrons = new Patron[5];

    @Override
    public void mouseClicked(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        Type[] typeList = Type.values();

        for(int i = 0; i < Type.values().length; i++) {
            if (mX >= (640 + (70 * i)) && mX <= (710 + (70 * i)) && mY >= 540 && mY <= 610 && gameTokens.get(typeList[i]) > 0) {
                gameTokens.replace(typeList[i], gameTokens.get(typeList[i]) - 1);
                break;
            }
        }
        repaint();
//        System.out.print(gameTokens);
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

        File file=new File("src/Image/cards.txt");
        Scanner scan;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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

        gameTokens = new HashMap<>();
        for(Type t: Type.values()) {
            if(t == Type.WILD)
                gameTokens.put(t, 5);
            else
                gameTokens.put(t, 7);
        }
    }

}
