import java.awt.*;
import java.awt.image.BufferedImage;
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

    public void draw(Graphics g, int x, int y, int scale) {

        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(new Color((int)(155 * Math.random()) + 100, (int)(155 * Math.random()) + 100, (int)(155 * Math.random()) + 100));
        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 4)));

        g.fillRect(x, y, (int) (1 * scale), 1 * scale);


        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 8)));


        int numTimes = 0;
        for (Type t : Type.values()) {
            int p = price.getOrDefault(t, 0);
            if (p != 0) {
                BufferedImage tokenImage = ImageHandler.getTokenImage(t);
                g.drawImage(tokenImage, (x) + (scale / 12), (int) ((y + (scale) * ((p - 1) / 8)) + 1.65 * (scale) - numTimes * (scale / 4)), scale / 4, scale / 4, null);
                g.setColor(Color.BLACK);
                g.drawString(p + "", (int) ((x) + (scale / 2.7)), (int) ((y + (scale) * ((p - 1) / 8)) + 1.825 * scale - numTimes * (scale / 4)));
                numTimes++;
            }
        }
        g.setColor(c);
        g.setFont(f);
    }
}