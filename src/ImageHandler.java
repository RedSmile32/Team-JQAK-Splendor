import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ImageHandler {
    static final BufferedImage MENU_BACKGROUND;
    static {
        boolean init = false;
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ImageHandler.class.getResource("Image/Background.jpg"));
            init = true;
        } catch (Exception e) {
            System.out.println("No image found for background");
        }
        if (init)
            MENU_BACKGROUND = temp;
        else
            MENU_BACKGROUND = null;
    }

    static final BufferedImage GAME_BACKGROUND;
    static {
        boolean init = false;
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(ImageHandler.class.getResource("Image/WoodBackground.png"));
            init = true;
        } catch (Exception e) {
            System.out.println("No image found for background");
        }
        if (init)
            GAME_BACKGROUND = temp;
        else
            GAME_BACKGROUND = null;
    }

    public static BufferedImage getTokenImage(Type type) {
        boolean b = false;
        BufferedImage image = null;
        try {
            System.out.println(type + ".png");
            image = ImageIO.read(ImageHandler.class.getResource("Image/" + type + ".png"));
            b = true;
        } catch (Exception e) {
            System.out.println("Image " + type + " Not Found");
        }

        return image;
    }
}
