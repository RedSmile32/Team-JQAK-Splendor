import java.util.HashMap;

public class card{
    private HashMap<Type, Integer> price = new HashMap<Type, Integer>();
    private boolean flipped;
    private Integer points;
    private Type discountColor;
    private Integer tier;
    public card( Integer points, Type discountColor, Integer tier, HashMap<Type, Integer> price){
        
        this.points = points;
        this.discountColor = discountColor;
        this.tier = tier;
        this.price = price;



    }



}