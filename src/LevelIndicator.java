import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Levels indicator.
 */
public class LevelIndicator implements Sprite {

    private String levels;

    /**
     * Instantiates a new Levels indicator.
     *
     * @param levels the levels
     */
    public LevelIndicator(String levels) {
        this.levels = levels;
    }

    /**
     * Draw sprite on the screen.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(500, 15, "Level Name: " + this.levels, 17);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add indicator to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
