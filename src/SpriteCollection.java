import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite to list.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites2 = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : sprites2) {
            s.timePassed();
        }
    }

    /**
     * Draw all sprites on the screen.
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}