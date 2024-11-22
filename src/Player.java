import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    HashMap<Type, ArrayList<Card>> cards = new HashMap<Type, ArrayList<Card>>();
    HashMap<Type, Integer> tokens = new HashMap<>();
    ArrayList<Patron> patrons = new ArrayList<>();
    Boolean p1;

    public Player(Boolean isP1) {
        p1 = isP1;
    }

    public boolean isP1() {
        return p1;
    }

    public boolean buyCard(Card c) {
        int temp = tokens.get(Type.WILD);
        for (Type f: Type.values()) {
            if (c.getPriceByColor(f) > tokens.get(f) + temp + getDiscount(f)) {
                if (c.getPriceByColor(f) - tokens.get(f) - getDiscount(f) > 0) {
                    temp -= c.getPriceByColor(f) - tokens.get(f) - getDiscount(f);
                }
            } else {
                return false;
            }
        }
        for (Type f: Type.values()) {
                if (c.getPriceByColor(f) - tokens.get(f) - getDiscount(f) > 0) {
                    tokens.put(Type.WILD, tokens.get(Type.WILD) - c.getPriceByColor(f) + tokens.get(f) + getDiscount(f));
                }
                tokens.put(f, tokens.get(f) - c.getPriceByColor(f) - getDiscount(f)); //this is wrong, correct it later.
                if (tokens.get(f) < 0) {
                    tokens.put(f, 0);
                }
        }

        //leaving off here.
        ArrayList<Card> tempcardlist = cards.get(c.getDiscountColor());
        tempcardlist.add(c);
        cards.put(c.getDiscountColor(), tempcardlist);
        return true;
    }

    public void addToken(Type t) {
        tokens.put(t, tokens.get(t) + 1);
    }

    public void removeToken(Type t) {
        tokens.put(t, tokens.get(t) - 1);
    }

    public int getScore() {
        int result = 0;
        for (Type f: Type.values()) {
            if (!f.equals(Type.WILD)) {
                for (Card c: cards.get(f)) {
                    result += c.getPoints();
                }
            }
            result += patrons.size() * 3;
        }

        return result;
    }

    public int getDiscount(Type t) {
        return cards.get(t).size();
    }

    public void checkPatron() {
        //incomplete, do later once patron class is more complete.
    }

    public boolean buyReservedCard(Card c) {
        if (tokens.get(Type.WILD) >= 3) {
            return false;
        }
        ArrayList<Card> temp = cards.get(Type.WILD);
        temp.add(c);
        cards.put(Type.WILD, temp);
        return true;
    }
}
