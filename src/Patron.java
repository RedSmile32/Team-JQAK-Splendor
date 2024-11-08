import java.util.HashMap;

public class Patron {
    Type type;
    HashMap<Type, Integer> price;
    String name;
    public Patron(HashMap<Type, Integer> price, String theString) {
        this.price = price;
        this.name 
    }

    public HashMap<Type, Integer> getPrice() {
        return price;
    }
}