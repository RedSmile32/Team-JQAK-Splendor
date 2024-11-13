import java.util.HashMap;

public class Patron {
    Type type;
    HashMap<Type, Integer> price;
    String name;
    public Patron(HashMap<Type, Integer> price, String name) {
        this.price = price;
        this.name = name;
    }
    //return price
    public HashMap<Type, Integer> getPrice() {
        return price;
    }
}