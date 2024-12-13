import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cardList;


    public Deck(ArrayList<Card> cards) {
        cardList = cards;
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public void addCard(Card c) {
        cardList.add(0, c); //since draw draws from the end of the list, we add cards to the front.
    }
}
