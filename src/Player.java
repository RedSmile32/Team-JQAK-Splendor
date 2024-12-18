import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Comparable {
    HashMap<Type, ArrayList<Card>> cards = new HashMap<Type, ArrayList<Card>>();
    HashMap<Type, Integer> tokens = new HashMap<>();
    ArrayList<Patron> patrons = new ArrayList<>();
    Boolean p1;
    int num;

    public Player(int num, Boolean isP1) {
        this.num = num;
        cards = new HashMap<>();
        for(Type type: Type.values()) {
            cards.put(type, new ArrayList<Card>());
        }
        tokens = new HashMap<>();
        for(Type type: Type.values()) {
            tokens.put(type, 0);
        }
        patrons = new ArrayList<>();
        p1 = isP1;
    }

    public boolean isP1() {
        return p1;
    }

    /**
     * takes in a card and determines if the player can buy the card.
     * if they can, the card will be added, tokens will be subracted and the method returns true.
     * if not, the method will return false.
     */
    public boolean buyCard(Card c) {
        //determine if the card can be bought
        int temp = tokens.get(Type.WILD);
        for (Type f: Type.values()) {
            if (!f.equals(Type.WILD)) {
                if (c.getPriceByColor(f) <= tokens.get(f) + temp + getDiscount(f)) {
                    if (c.getPriceByColor(f) - tokens.get(f) - getDiscount(f) > 0) {
                        temp -= c.getPriceByColor(f) - tokens.get(f) - getDiscount(f);
                    }
                } else {
                    System.out.println(c.getPriceByColor(f));
                    System.out.println(f);
                    return false;
                }
            }
        }
        //subtract the tokens needed to buy the card
        for (Type f: Type.values()) {
            //subtract wild tokens first
            if (c.getPriceByColor(f) - tokens.get(f) - getDiscount(f) > 0) {
                tokens.put(Type.WILD, tokens.get(Type.WILD) - c.getPriceByColor(f) + tokens.get(f) + getDiscount(f));
            }
            //only subtracts tokens if the discount doesnt cover everything
            if (getDiscount(f) < c.getPriceByColor(f)) {
                tokens.put(f, tokens.get(f) - c.getPriceByColor(f) + getDiscount(f)); //this is wrong, correct it later.
            }

        }

        //adds the card to the cards hashmap
        ArrayList<Card> tempcardlist = cards.get(c.getDiscountColor());
        tempcardlist.add(c);
        cards.put(c.getDiscountColor(), tempcardlist);
        return true;
    }

    /**
     * adds a token to the player of the color t
     * @param t
     */
    public void addToken(Type t) {
        tokens.put(t, tokens.get(t) + 1);
    }

    /**
     * removes a token of the color t
     * @param t
     */
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
        }
        result += patrons.size() * 3;

        return result;
    }

    public int getDiscount(Type t) {
        return cards.get(t).size();
    }

    public void checkPatron() {
        //incomplete, do later once patron class is more complete.
    }

    /**
     * reserves the card instead of buying it. returns false if the player has more than 3 reserved
     * @param c
     * @return
     */
    public boolean buyReservedCard(Card c) {
        if (cards.get(Type.WILD).size() >= 3) {
            return false;
        }
        ArrayList<Card> temp = cards.get(Type.WILD);
        temp.add(c);
        cards.put(Type.WILD, temp);
        return true;
    }

    public void setP1(Boolean b) {
        p1 = b;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null)
            throw new NullPointerException();
        if(!(o instanceof Player))
            throw new ClassCastException();
        Player other = (Player) o;
        if(this.getScore()>other.getScore())
            return 1;
        else if(this.getScore()<other.getScore())
            return -1;
        else {
            if(this.num>other.num)
                return 1;
            else if(this.num<other.num)
                return -1;
            else
                return 0;
        }
    }
}
