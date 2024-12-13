import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Patron {
    Type type;
    HashMap<Type, Integer> price;
    String name;
    int owner;
    int r;
    int gr;
    int b;

    public static Patron[] patronList = new Patron[10];


    public Patron(HashMap<Type, Integer> price, String theString) {
        this.price = price;
        this.name = theString;
        r = (int)(155 * Math.random()) + 100;
        gr = (int)(155 * Math.random()) + 100;
        b = (int)(155 * Math.random()) + 100;
    }

    public HashMap<Type, Integer> getPrice() {
        return price;
    }

    public static void setupPatrons() {
        HashMap<Type, Integer> temp0 = new HashMap<>();
        HashMap<Type, Integer> temp1 = new HashMap<>();
        HashMap<Type, Integer> temp2 = new HashMap<>();
        HashMap<Type, Integer> temp3 = new HashMap<>();
        HashMap<Type, Integer> temp4 = new HashMap<>();
        HashMap<Type, Integer> temp5 = new HashMap<>();
        HashMap<Type, Integer> temp6 = new HashMap<>();
        HashMap<Type, Integer> temp7 = new HashMap<>();
        HashMap<Type, Integer> temp8 = new HashMap<>();
        HashMap<Type, Integer> temp9 = new HashMap<>();


        if (patronList[0] == null) {
            temp0.put(Type.WHITE, 0);
            temp0.put(Type.BLACK, 0);
            temp0.put(Type.RED, 4);
            temp0.put(Type.GREEN, 4);
            temp0.put(Type.BLUE, 0);
            patronList[0] = new Patron(temp0, "string goes here, why do we need a string again?");
            temp1.put(Type.WHITE, 3);
            temp1.put(Type.BLACK, 3);
            temp1.put(Type.RED, 3);
            temp1.put(Type.GREEN, 0);
            temp1.put(Type.BLUE, 0);
            patronList[1] = new Patron(temp1, "string goes here, why do we need a string again?");
            temp2.put(Type.WHITE, 4);
            temp2.put(Type.BLACK, 0);
            temp2.put(Type.RED, 0);
            temp2.put(Type.GREEN, 0);
            temp2.put(Type.BLUE, 4);
            patronList[2] = new Patron(temp2, "string goes here, why do we need a string again?");
            temp3.put(Type.WHITE, 4);
            temp3.put(Type.BLACK, 4);
            temp3.put(Type.RED, 0);
            temp3.put(Type.GREEN, 0);
            temp3.put(Type.BLUE, 0);
            patronList[3] = new Patron(temp3, "string goes here, why do we need a string again?");
            temp4.put(Type.WHITE, 0);
            temp4.put(Type.BLACK, 0);
            temp4.put(Type.RED, 0);
            temp4.put(Type.GREEN, 4);
            temp4.put(Type.BLUE, 4);
            patronList[4] = new Patron(temp4, "string goes here, why do we need a string again?");
            temp5.put(Type.WHITE, 0);
            temp5.put(Type.BLACK, 0);
            temp5.put(Type.RED, 3);
            temp5.put(Type.GREEN, 3);
            temp5.put(Type.BLUE, 3);
            patronList[5] = new Patron(temp5, "string goes here, why do we need a string again?");
            temp6.put(Type.WHITE, 3);
            temp6.put(Type.BLACK, 0);
            temp6.put(Type.RED, 0);
            temp6.put(Type.GREEN, 3);
            temp6.put(Type.BLUE, 3);
            patronList[6] = new Patron(temp6, "string goes here, why do we need a string again?");
            temp7.put(Type.WHITE, 0);
            temp7.put(Type.BLACK, 4);
            temp7.put(Type.RED, 4);
            temp7.put(Type.GREEN, 0);
            temp7.put(Type.BLUE, 0);
            patronList[7] = new Patron(temp7, "string goes here, why do we need a string again?");
            temp8.put(Type.WHITE, 3);
            temp8.put(Type.BLACK, 3);
            temp8.put(Type.RED, 0);
            temp8.put(Type.GREEN, 0);
            temp8.put(Type.BLUE, 3);
            patronList[8] = new Patron(temp8, "string goes here, why do we need a string again?");
            temp9.put(Type.WHITE, 0);
            temp9.put(Type.BLACK, 3);
            temp9.put(Type.RED, 3);
            temp9.put(Type.GREEN, 3);
            temp9.put(Type.BLUE, 0);
            patronList[9] = new Patron(temp9, "string goes here, why do we need a string again?");
        }

    }

    public void draw(Graphics g, int x, int y, int scale) {

        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(new Color(r, gr, b));
        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 4)));

        g.fillRect(x, y, (int) (1 * scale), 1 * scale);


        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 8)));


        int numTimes = 0;
        for (Type t : Type.values()) {
            int p = price.getOrDefault(t, 0);
            if (p != 0) {
                BufferedImage tokenImage = ImageHandler.getTokenImage(t);
                g.drawImage(tokenImage, (x) + (scale / 12), (int) ((y + (scale) * 0.7  - numTimes * (scale / 4))), scale / 4, scale / 4, null);
                g.setColor(Color.BLACK);
                g.drawString(p + "", (int) ((x) + (scale / 2.7)), (int) ((y + (scale) * 0.9  - numTimes * (scale / 4))));
                numTimes++;
            }
        }
        g.setColor(c);
        g.setFont(f);
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int player) {
        owner = player;
    }
}