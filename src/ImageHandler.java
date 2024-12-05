import java.awt.image.BufferedImage;
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
}
