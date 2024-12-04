import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageHandler {
    public static BufferedImage background() {
        BufferedImage background;
        try {
            background = ImageIO.read(ImageHandler.class.getResource("Image/Background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return background;
    }

    public static BufferedImage gameBackground() {
        BufferedImage background;
        try {
            background = ImageIO.read(ImageHandler.class.getResource("Image/WoodBackground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return background;
    }

}
