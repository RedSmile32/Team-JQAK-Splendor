import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Card {
    private HashMap<Type, Integer> price = new HashMap<Type, Integer>();
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
    public int getPriceByColor(Type t) {
        return price.getOrDefault(t, 0);
    }

    public int getPoints() {
        return points;
    }
    public int getTier() {return tier;}

    public ArrayList<Type> getNonZeroTypes() {
        ArrayList<Type> nonZeros = new ArrayList<Type>();
        for(Type t: Type.values()) {
            if(price.getOrDefault(t, 0)!=0)
                nonZeros.add(t);
        }
        return nonZeros;
    }

    public void draw(Graphics g, int x, int y, int scale) {
        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 4)));

        g.fillRect(x, y, (int) (1.5 * scale), 2 * scale);
        g.setColor(new Color(200, 200, 200));
        g.fillRect(x, y, (int) (1.5 * scale), (int) (0.5 * scale));
        g.setColor(Color.BLACK);
        g.fillRect(x, (int) (y + 0.5 * scale), (int) (1.5 * scale), (int) (0.025 * scale));

        g.drawString("" + points, (int) (x + (1.2 * scale)), (int) (y + (0.4 * scale)));

        g.setFont(new Font("SansSerif", Font.PLAIN, (int) Math.round(((double) scale) / 8)));

        BufferedImage discountImage = ImageHandler.getTokenImage(discountColor);
        g.drawImage(discountImage, (int) (x + (0.05 * scale)), (int) (y + (0.05 * scale)), (int) (0.4 * scale), (int) (0.4 * scale), null);

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