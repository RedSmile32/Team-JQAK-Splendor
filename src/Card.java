import java.util.HashMap;

public class Card {
    private HashMap<Type, Integer> price = new HashMap<Type, Integer>();
    private boolean flipped;
    private Integer points;
    private Type discountColor;
    private Integer tier;

    public Card(Integer points, Type discountColor, Integer tier, HashMap<Type, Integer> price) {
        this.points = points;
        this.discountColor = discountColor;
        this.tier = tier;
        this.price = price;
    }
    public Type getDiscountColor(){
        return discountColor;
    }
//    public draw(int, int, int, Graphics){
//
//    }
    public boolean getFlip(){
        return flipped;
    }
    public void flip(){
        flipped = !flipped;
    }
    public int getPriceByColor(Type t) {
        return price.get(t);
    }

    public int getPoints() {
        return points;
    }
    public int getTier() {return tier;}
}