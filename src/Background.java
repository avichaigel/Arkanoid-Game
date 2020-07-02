import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Background.
 */
public class Background implements Sprite {

    private String level;
    private int width;
    private int height;
    private Color color;
    private Image image;

    /**
     * Instantiates a new Background.
     *
     * @param level the level
     */
    public Background(String level) {
        this.level = level;
    }

    /**
     * Instantiates a new Background.
     *
     * @param width  the width
     * @param height the height
     * @param color  the color
     * @param image  the image
     */
    public Background(Color color, Image image, int width, int height) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.image = image;
    }

    /**
     * First.
     *
     * @param d the d
     */
    public void first(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(Color.BLUE);
        d.drawCircle(395, 200, 50);
        d.drawCircle(395, 200, 75);
        d.drawCircle(395, 200, 100);
        d.drawLine(415, 195, 515, 195);
        d.drawLine(375, 195, 255, 195);
        d.drawLine(395, 175, 395, 55);
        d.drawLine(395, 215, 395, 335);
    }

    /**
     * Second.
     *
     * @param d the d
     */
    public void second(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(new Color(0xEEE6AF));
        d.fillCircle(133, 100, 70);
        for (int i = 0; i < 80; i++) {
            d.drawLine(133, 100, i * 10, 250);
        }
        d.setColor(new Color(0xEBD649));
        d.fillCircle(133, 100, 60);
        d.setColor(new Color(0xFDE018));
        d.fillCircle(133, 100, 50);
    }

    /**
     * Third.
     *
     * @param d the d
     */
    public void third(DrawSurface d) {
        d.setColor(new Color(0x2A8115));
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(Color.black);
        d.fillRectangle(50, 450, 80, 150);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(55 + (j * 15), 455 + (i * 35), 10, 30);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(75, 390, 30, 60);
        d.setColor(new Color(0x4E4A49));
        d.fillRectangle(85, 210, 10, 180);
        d.setColor(new Color(0xAAA051));
        d.fillCircle(90, 195, 15);
        d.setColor(new Color(0xF05D3E));
        d.fillCircle(90, 195, 10);
        d.setColor(Color.white);
        d.fillCircle(90, 195, 5);
    }

    /**
     * Fourth.
     *
     * @param d the d
     */
    public void fourth(DrawSurface d) {
        d.setColor(new Color(0x1787CF));
        d.fillRectangle(0, 20, 800, 580);
        d.setColor(Color.white);
        for (int i = 0; i < 12; i++) {
            d.drawLine(60 + (i * 10), 350, 40 + (i * 10), 600);
        }
        for (int j = 0; j < 10; j++) {
            d.drawLine(620 + (j * 10), 530, 590 + (j * 10), 600);
        }
        d.setColor(new Color(0xCBCBCB));
        d.fillCircle(50, 350, 30);
        d.fillCircle(70, 375, 40);
        d.fillCircle(600, 530, 20);
        d.fillCircle(630, 550, 30);
        d.setColor(new Color(0xBABABA));
        d.fillCircle(100, 345, 50);
        d.fillCircle(640, 510, 40);
        d.setColor(new Color(0xA9A9A9));
        d.fillCircle(120, 370, 25);
        d.fillCircle(140, 360, 50);
        d.fillCircle(670, 540, 15);
        d.fillCircle(685, 535, 40);
        d.setColor(Color.white);
    }

    /**
     * Draw sprite on the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (level != null) {
            switch (level) {
                case "first":
                    this.first(d);
                    break;
                case "second":
                    this.second(d);
                    break;
                case "third":
                    this.third(d);
                    break;
                case "fourth":
                    this.fourth(d);
                    break;
                default:
                    break;
            }
        } else {
            if (this.color != null) {
                d.setColor(this.color);
                d.fillRectangle(0, 0, this.width, this.height);
            } else {
                d.drawImage(0, 0, this.image);
            }
            d.setColor(Color.ORANGE);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
