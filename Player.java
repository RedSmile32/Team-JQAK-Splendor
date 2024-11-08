import java.util.HashMap;
import java.util.ArrayList;
public class Player{
    private HashMap<Type, ArrayList<Card>> cards= new HashMap<Type, ArrayList<Card>>;
    private HashMap<Type, Integer> tokens = new HashMap<Type, Integer>;
    private ArrayList<Patron> patrons; 
    private boolean p1;

    public Player(boolean x){
        p1 = x;
    }
    public boolean isP1(){
        return p1;

    }
    public boolean buyCard(Card){
        return true;
        


    }
    public void addToken(Type)


}