import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
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


public class GamePanel extends JPanel implements MouseListener, KeyListener {
    Player[] players = new Player[4];
    Deck[] decks = new Deck[3];
    HashMap<Integer, Card[]> displayedCards;
    HashMap<Type, Integer> gameTokens;
    BufferedImage title;
    Patron[] patrons;
    int activePlayer;
    Type prevClicked;

    /**
     * turnState is a variable that determines the state of the current turn in case the turn requires multiple clicks.
     * For turns that involve a single click:
     *     - To buy a card, if turnState = 0 and a card is clicked on, the game checks if the card can be bought and
     *     buys it and moves on if it can. If it can't, turnState is set to 0 and the turn is restarted.
     *     - To buy a reserved card, if turnState = 0 and the reserved card slot is clicked on, turnState is set to 1
     *     and a popup opens allowing the player to select the reserved card they want to buy. If they can, the card is
     *     bought and next player gets their turn. If it can't, turnState is set to 0 and the turn restarts.
     * For turns that involve more than 1 click:
     *     - When a token that is in the game inventory is clicked, the token is given to the player if it can be and
     *     turnState is set to 2. Then, two things can happen:
     *         - If a token of the same type is selected, the player is given a token and the turn moves on.
     *             - If there aren't enough, the token already given is taken away and the player is prompted to make
     *             another move.
 *             - If a token of a different type is selected, turnState is set to 3 and the player is given it. Another
     *             token is then selected and then the turn moves to the next player.
     */
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
        if(turnState==0) {
            prevClicked=null;
        }
        for(Type t: Type.values()) {
            if(t != Type.WILD) {
                int sum = 0;
                for(Player p: players) {
                    sum+=p.tokens.get(t);
                }
                sum += gameTokens.get(t);
                while(sum!=7) {
                    if (sum > 7) {
                        gameTokens.put(t, gameTokens.get(t) - 1);
                        sum--;
                    }
                    if (sum < 7) {
                        gameTokens.put(t, gameTokens.get(t) + 1);
                        sum++;
                    }
                }
            }
        }

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

        if (patrons[0] != null) {
            for (int i = 0; i < 5; i++) {
                patrons[i].draw(g, 180 + i*90, 30, 80);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                displayedCards.get(2-i)[j].draw(g, 100 * j + 200, 130 * i + 230, 60);
            }
        }

        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        for(int i = 0; i < Type.values().length; i++) {
            g.drawImage(ImageHandler.getTokenImage(Type.values()[i]), 640+(70 * i), 540, 70, 70, null);
            g.drawString(gameTokens.get(Type.values()[i])+"", 665+(70 * i), 640);
        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(turnState == 0) {
            boolean clickedOnCard = false;
            Point d = null;
            Type clicked = null;
            if(200<=x&&x<=290&&230<=y&&y<=350)
                d = new Point(2, 0);
            else if(300<=x&&x<=390&&230<=y&&y<=350)
                d = new Point(2, 1);
            else if(400<=x&&x<=490&&230<=y&&y<=350)
                d = new Point(2, 2);
            else if(500<=x&&x<=590&&230<=y&&y<=350)
                d = new Point(2, 3);
            else if(200<=x&&x<=290&&360<=y&&y<=480)
                d = new Point(1, 0);
            else if(300<=x&&x<=390&&360<=y&&y<=480)
                d = new Point(1, 1);
            else if(400<=x&&x<=490&&360<=y&&y<=480)
                d = new Point(1, 2);
            else if(500<=x&&x<=590&&360<=y&&y<=480)
                d = new Point(1, 3);
            else if(200<=x&&x<=290&&490<=y&&y<=610)
                d = new Point(0, 0);
            else if(300<=x&&x<=390&&490<=y&&y<=610)
                d = new Point(0, 1);
            else if(400<=x&&x<=490&&490<=y&&y<=610)
                d = new Point(0, 2);
            else if(500<=x&&x<=590&&490<=y&&y<=610)
                d = new Point(0, 3);
            else if(640<=x&&x<710&&540<=y&&y<=610)
                clicked = Type.WHITE;
            else if(710<=x&&x<780&&540<=y&&y<=610)
                clicked = Type.BLACK;
            else if(780<=x&&x<850&&540<=y&&y<=610)
                clicked = Type.RED;
            else if(850<=x&&x<920&&540<=y&&y<=610)
                clicked = Type.GREEN;
            else if(920<=x&&x<990&&540<=y&&y<=610)
                clicked = Type.BLUE;
            else if(990<=x&&x<1060&&540<=y&&y<=610)
                clicked = Type.WILD;
            if(d!=null) {
                int wilds = players[activePlayer].tokens.get(Type.WILD);
                boolean b = players[activePlayer].buyCard(displayedCards.get(d.x)[d.y]);
                if(b) {
                    int wilds2 = players[activePlayer].tokens.get(Type.WILD);
                    Card[] row = displayedCards.get(d.x);
                    for(Type t: row[d.y].getNonZeroTypes()) {
                        gameTokens.put(t, gameTokens.get(t)+row[d.y].getPriceByColor(t)-players[activePlayer].getDiscount(t));
                    }
                    row[d.y] = decks[d.x].cardList.removeLast();
                    displayedCards.put(d.x, row);
                }
            } else if(clicked!=null) {
                if(gameTokens.get(clicked)>0) {
                    if(clicked != Type.WILD || players[activePlayer].tokens.get(Type.WILD) < 3)
                        gameTokens.put(clicked, gameTokens.get(clicked)-1);
                    if(clicked==Type.WILD && players[activePlayer].cards.get(Type.WILD).size()<3) {
                        players[activePlayer].addToken(clicked);
                        turnState = 1;
                    }
                    else if(clicked != Type.WILD) {
                        players[activePlayer].addToken(clicked);
                        turnState = 2;
                    }
                }
            }
        } else if (turnState==1) {
            Point d = null;
            if(200<=x&&x<=290&&230<=y&&y<=350)
                d = new Point(2, 0);
            else if(300<=x&&x<=390&&230<=y&&y<=350)
                d = new Point(2, 1);
            else if(400<=x&&x<=490&&230<=y&&y<=350)
                d = new Point(2, 2);
            else if(500<=x&&x<=590&&230<=y&&y<=350)
                d = new Point(2, 3);
            else if(200<=x&&x<=290&&360<=y&&y<=480)
                d = new Point(1, 0);
            else if(300<=x&&x<=390&&360<=y&&y<=480)
                d = new Point(1, 1);
            else if(400<=x&&x<=490&&360<=y&&y<=480)
                d = new Point(1, 2);
            else if(500<=x&&x<=590&&360<=y&&y<=480)
                d = new Point(1, 3);
            else if(200<=x&&x<=290&&490<=y&&y<=610)
                d = new Point(0, 0);
            else if(300<=x&&x<=390&&490<=y&&y<=610)
                d = new Point(0, 1);
            else if(400<=x&&x<=490&&490<=y&&y<=610)
                d = new Point(0, 2);
            else if(500<=x&&x<=590&&490<=y&&y<=610)
                d = new Point(0, 3);
            if(d!=null) {
                boolean b = players[activePlayer].buyReservedCard(displayedCards.get(d.x)[d.y]);
                if(b) {
                    Card[] row = displayedCards.get(d.x);
                    row[d.y] = decks[d.x].cardList.removeFirst();
                    displayedCards.put(d.x, row);
                    turnState = 0;

                } else {
                    players[activePlayer].removeToken(Type.WILD);
                    turnState = 0;
                }
            }
        } else if (turnState == 2) {
            Type clicked = null;
            if(640<=x&&x<710&&540<=y&&y<=610)
                clicked = Type.WHITE;
            else if(710<=x&&x<780&&540<=y&&y<=610)
                clicked = Type.BLACK;
            else if(780<=x&&x<850&&540<=y&&y<=610)
                clicked = Type.RED;
            else if(850<=x&&x<920&&540<=y&&y<=610)
                clicked = Type.GREEN;
            else if(920<=x&&x<990&&540<=y&&y<=610)
                clicked = Type.BLUE;
            else if(990<=x&&x<1060&&540<=y&&y<=610)
                clicked = Type.WILD;
            if(clicked == prevClicked) {
                if(gameTokens.getOrDefault(clicked, 0)>=3) {
                    players[activePlayer].addToken(clicked);
                    turnState = 0;
                }
            }
        }
        System.out.println(turnState);
        repaint();
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
        Patron.setupPatrons();
        for (int i = 0; i < 5; i++) {

            patrons[i] = Patron.patronList[(int) (Math.random() * 10)];
            for (int j = 0; j< i; j++) {
                if (patrons[i].equals(patrons[j])) {
                    i--;
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
        activePlayer = 0;

        displayedCards = new HashMap<>();
        for(int i = 0; i < 3; i++) {
            Card[] row = new Card[4];
            for(int j = 0; j < 4; j++) {
                row[j]=decks[i].cardList.removeLast();
            }
            displayedCards.put(i, row);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(turnState == 1) {
            if(e.getKeyChar()=='1') {
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
