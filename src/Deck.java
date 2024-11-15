import java.awt.*;
import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cardList;


    public Deck(ArrayList<Card> cards) {
        cardList = cards;
    }

    public void shuffle() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card n: cardList) {
            result.add((int) (Math.random() * result.size()), n);
        }
        cardList = result;
    }

    public void addCard(Card c) {
        cardList.add(0, c); //since draw draws from the end of the list, we add cards to the front.
    }

    public void paint(int a, int b, int c, Graphics g) {
        //aditya, i have no idea why we need three variables that are ints,
        //so im gonna leave this method to you.

    }

}
