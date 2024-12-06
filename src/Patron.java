import java.util.HashMap;

public class Patron {
    Type type;
    HashMap<Type, Integer> price;
    String name;

    public static Patron[] patronList = new Patron[10];


    public Patron(HashMap<Type, Integer> price, String theString) {
        this.price = price;
        this.name = theString;
    }

    public HashMap<Type, Integer> getPrice() {
        return price;
    }

    public void setupPatrons() {
        HashMap<Type, Integer> temp = new HashMap<>();


        temp.put(Type.WHITE, 0);
        temp.put(Type.BLACK, 0);
        temp.put(Type.RED, 4);
        temp.put(Type.GREEN, 4);
        temp.put(Type.BLUE, 0);
        patronList[0] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 3);
        temp.put(Type.BLACK, 3);
        temp.put(Type.RED, 3);
        temp.put(Type.GREEN, 0);
        temp.put(Type.BLUE, 0);
        patronList[1] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 4);
        temp.put(Type.BLACK, 0);
        temp.put(Type.RED, 0);
        temp.put(Type.GREEN, 0);
        temp.put(Type.BLUE, 4);
        patronList[2] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 4);
        temp.put(Type.BLACK, 4);
        temp.put(Type.RED, 0);
        temp.put(Type.GREEN, 0);
        temp.put(Type.BLUE, 0);
        patronList[3] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 0);
        temp.put(Type.BLACK, 0);
        temp.put(Type.RED, 0);
        temp.put(Type.GREEN, 4);
        temp.put(Type.BLUE, 4);
        patronList[4] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 0);
        temp.put(Type.BLACK, 0);
        temp.put(Type.RED, 3);
        temp.put(Type.GREEN, 3);
        temp.put(Type.BLUE, 3);
        patronList[5] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 3);
        temp.put(Type.BLACK, 0);
        temp.put(Type.RED, 0);
        temp.put(Type.GREEN, 3);
        temp.put(Type.BLUE, 3);
        patronList[6] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 0);
        temp.put(Type.BLACK, 4);
        temp.put(Type.RED, 4);
        temp.put(Type.GREEN, 0);
        temp.put(Type.BLUE, 0);
        patronList[7] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 3);
        temp.put(Type.BLACK, 3);
        temp.put(Type.RED, 0);
        temp.put(Type.GREEN, 0);
        temp.put(Type.BLUE, 3);
        patronList[8] = new Patron(temp, "string goes here, why do we need a string again?");
        temp.put(Type.WHITE, 0);
        temp.put(Type.BLACK, 3);
        temp.put(Type.RED, 3);
        temp.put(Type.GREEN, 3);
        temp.put(Type.BLUE, 0);
        patronList[9] = new Patron(temp, "string goes here, why do we need a string again?");


    }
}