import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Block apply color.
 */
public class UseColorBl implements BlockAppearance {
    private Color color;

    /**
     * the constructor.
     *
     * @param colorr the color.
     */
    public UseColorBl(Color colorr) {
        this.color = colorr;
    }

    @Override
    public void fill(Rectangle rect, DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * Draw.
     *
     * @param rect         the rect
     * @param d the draw surface
     */
    @Override
    public void draw(Rectangle rect, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
}
