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
    BufferedImage green_token;
    BufferedImage wild_token;
    BufferedImage red_token;
    BufferedImage blue_token;
    BufferedImage white_token;
    BufferedImage black_token;
    
    BufferedImage Patron1;
    BufferedImage Patron2;
    BufferedImage Patron3;
    BufferedImage Patron4;
    BufferedImage Patron5;

    public GamePanel() {
        try {
            title = ImageIO.read(GamePanel.class.getResource("Image/SplendorTitle.png"));
            green_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorGreen1.jpg")));
            wild_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorWild.jpg")));
            red_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorRed.jpg")));
            blue_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorBlue.jpg")));
            white_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorWhite.jpg")));
            black_token = ImageIO.read(GamePanel.class.getResource(("/Image/SplendorBlack.jpg")));
            
            Patron1 = ImageIO.read(GamePanel.class.getResource(("/PatronFolder/patron1.png")));
            Patron2 = ImageIO.read(GamePanel.class.getResource(("/PatronFolder/patron2.png")));
            Patron3 = ImageIO.read(GamePanel.class.getResource(("/PatronFolder/patron3.png")));
            Patron4 = ImageIO.read(GamePanel.class.getResource(("/PatronFolder/patron4.png")));
            Patron5 = ImageIO.read(GamePanel.class.getResource(("/PatronFolder/patron5.png")));

            
        } catch (Exception e) {
            System.out.println("Resource Location Failure");
        }
        addMouseListener(this);


        //Game initialization occurs here

        players[0] = new Player(true);
        players[1] = new Player(false);
        players[2] = new Player(false);
        players[3] = new Player(false);


        decks[0] = new Deck(new ArrayList<Card>());

    }

    public void paint(Graphics g) {
        g.drawImage(ImageHandler.GAME_BACKGROUND, 0, 0, getWidth(), getHeight(), null);

        g.drawImage(green_token, 80, 540, 100, 100, null);
        g.drawImage(black_token, 200, 540, 100, 100, null);
        g.drawImage(red_token, 320, 540, 100, 100, null);
        g.drawImage(blue_token, 440, 540, 100, 100, null);
        g.drawImage(white_token, 560, 540, 100, 100, null);
        g.drawImage(wild_token, 680, 540, 100, 100, null);
        //draws all the tokens
        
        g.drawImage(Patron1, 80, 50, 100, 100, null);
        g.drawImage(Patron2, 180, 50, 100, 100, null);
        g.drawImage(Patron3, 280, 50, 100, 100, null);
        g.drawImage(Patron4, 380, 50, 100, 100, null);
        g.drawImage(Patron5, 480, 50, 100, 100, null);
        //draws all the patrons
        

        g.setColor(Color.BLACK);
        g.fillRect(0, getWidth()/8-10, getWidth()/2, 4);
        g.fillRect(getWidth()/2-2, 0, 4, 520);
        g.fillRect(0, 520, getWidth(), 4);
        g.fillRect(635, 268, 1280, 4);
        g.fillRect(958, 0, 4, 520);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("SansSerif", Font.PLAIN, 40));
        g.drawString("Nobles", 40, 40);
        g.drawString("Player 1: " + players[0].getScore(), 640, 40);
        g.drawString("Player 2: " + players[1].getScore(), 970, 40);
        g.drawString("Player 3: " + players[2].getScore(), 640, 310);
        g.drawString("Player 4: " + players[3].getScore(), 970, 310);

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
