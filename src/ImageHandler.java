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
    static final BufferedImage TITLE;
    static {
        boolean init = false;
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(GamePanel.class.getResource("Image/SplendorTitle.png"));
            init = true;
        } catch (Exception e) {
            System.out.println("Title not found");
        }
        if(init)
            TITLE = temp;
        else
            TITLE = null;
    }

    public static BufferedImage getTokenImage(Type type) {
        boolean b = false;
        BufferedImage image = null;
        try {
            image = ImageIO.read(ImageHandler.class.getResource("Image/" + type + ".png"));
            b = true;
        } catch (Exception e) {
            System.out.println("Image " + type + " Not Found");
        }

        return image;
    }

    public static BufferedImage endBackground() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ImageHandler.class.getResource("Image/EndPanelBackground.png"));
        } catch (Exception e) {
            System.out.println("Image Not Found");
        }

        return image;
    }
}
