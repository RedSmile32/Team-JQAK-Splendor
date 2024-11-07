import java.util.HashMap;

public class Patron() {
    // enum Type {
    //     LOW,
    //     MEDIUM,
    //     HIGHEUIRGIURGEHUIRGEHGREURGEHUIRGHEREHIUREGHUIRGHEIUGREHGERHUIREGH
    // }
    HashMap<Type, Integer> price;
    public Patron(HashMap<Type, Integer> theMap, String theString) {
        price = new HashMap<Type, Integer>();
    }

    public HashMap<Type, Integer> getPrice() {
        return price;
    }
}