import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.*;




public class GamePanel extends JPanel implements MouseListener {
    Player[] players = new Player[4];
    Deck[] decks = new Deck[3];

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

        //turn text into card
        int points = Integer.parseInt(scan.next());
        Type discountcolor = Type.valueOf(scan.next());
        int tier = Integer.parseInt(scan.next());

        HashMap<Type, Integer> prices = new HashMap<>();
        prices.put(Type.BLACK, Integer.parseInt(scan.next()));
        prices.put(Type.WHITE, Integer.parseInt(scan.next()));
        prices.put(Type.RED, Integer.parseInt(scan.next()));
        prices.put(Type.BLUE, Integer.parseInt(scan.next()));
        prices.put(Type.GREEN, Integer.parseInt(scan.next()));

        temp.add(new Card(points, discountcolor, tier, prices));



    }
}
