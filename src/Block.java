import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //fields
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Map<Integer, BlockAppearance> blockAppearances;
    private int hits;

    /**
     * Instantiates a new Block.
     *
     * @param rect  the rect
     * @param color the color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rect              the rect
     * @param blockAppearances1 the block appearances 1
     * @param hits1             the hits 1
     * @param stroke            the stroke
     */
    public Block(Rectangle rect, Map<Integer, BlockAppearance> blockAppearances1, int hits1, Color stroke) {
        this.rect = rect;
        this.blockAppearances = blockAppearances1;
        this.hits = hits1;
        this.color = stroke;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notify the object that we collided with at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          the ball that hits the object
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.pointOnLine(collisionPoint, rect.getLeftBrd())
                || this.pointOnLine(collisionPoint, rect.getRightBrd())) {
            this.hits -= 1;
            this.notifyHit(hitter);
            return new Velocity(-(currentVelocity.getDx()), currentVelocity.getDy());
        } else if (this.pointOnLine(collisionPoint, rect.getTopBrd())
                || this.pointOnLine(collisionPoint, rect.getBottomBrd())) {
            this.hits -= 1;
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -(currentVelocity.getDy()));
        } else {
            return null;
        }
    }

    /**
     * check if collision point is on a border.
     *
     * @param collisionPoint the collision point
     * @param line           the line
     * @return the boolean
     */
    public boolean pointOnLine(Point collisionPoint, Line line) {
        double xP = collisionPoint.getX(), yP = collisionPoint.getY(), xC = line.start().getX(),
                xD = line.end().getX(), yC = line.start().getY(), yD = line.end().getY();
        CheckDouble cd = new CheckDouble();
        if (cd.checkDouble(xP, Math.max(xC, xD)) && cd.checkDouble(Math.min(xC, xD), xP)
                && cd.checkDouble(yP, Math.max(yC, yD)) && cd.checkDouble(Math.min(yC, yD), yP)) {
            return true;
        }
        return false;
    }

    /**
     * Draw sprite on the screen.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        if (this.blockAppearances != null) {
            if (this.blockAppearances.containsKey(this.hits)) {
                this.blockAppearances.get(this.hits).fill(this.rect, d);
            } else {
                this.blockAppearances.get(1).fill(this.rect, d);
            }
        } else {
            d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
        if (this.color != null) {
            d.setColor(this.color);
            d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Add block to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove block from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notify the listeners that there was a hit.
     *
     * @param hitter the ball that hits the object
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

    /**
     * Add hit listener.
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets hits.
     *
     * @return the hits
     */
    public int getHits() {
        return hits;
    }
}
