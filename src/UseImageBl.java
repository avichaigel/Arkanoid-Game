import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;


public class UseImageBl implements BlockAppearance {
    private Image img;

    /**
     * the constructor.
     * @param path the image.
     */
    public UseImageBl(String path) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            this.img = null;
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fill(Rectangle rect, DrawSurface d) {
        if (this.img != null) {
            d.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), this.img);
        } else {
            d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
        }
    }

    /**
     * Draw.
     *
     * @param rect         the rect
     * @param d the draw surface
     */
    @Override
    public void draw(Rectangle rect, DrawSurface d) {
        return;
    }
}
