import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    /**
     * The constant TOP_OR_LEFT_BORDER.
     */
//fields
    public static final int TOP_BORDER = 21;
    /**
     * The constant BOTTOM_BORDER.
     */
    public static final int BOTTOM_BORDER = 599;
    /**
     * The constant RIGHT_BORDER.
     */
    public static final int RIGHT_BORDER = 779;
    /**
     * The constant LEFT_BORDER.
     */
    public static final int LEFT_BORDER = 1;

    private Point center;
    private Point lBord;
    private Point rBord;
    private int r;
    private Color color;
    private Velocity v;
    private GameEnvironment gameN;


    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
// constructors
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x of the point
     * @param y     the y of the point
     * @param r     the radius
     * @param color the color
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x of the point
     * @param y     the y of the point
     * @param r     the radius
     * @param color the color
     * @param lBord the left border
     * @param rBord the right border
     */
    public Ball(int x, int y, int r, Color color, Point lBord, Point rBord) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.lBord = lBord;
        this.rBord = rBord;
    }

    // accessors

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw balls on a given surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), r);
    }

    /**
     * Sets velocity.
     *
     * @param vel the v
     */
    public void setVelocity(Velocity vel) {

        this.v = vel;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * Sets game env.
     *
     * @param gameNv the game n
     */
    public void setGameEnv(GameEnvironment gameNv) {
        this.gameN = gameNv;
    }

    /**
     * Gets game env.
     *
     * @return the game env
     */
//return a GameEnvironment object
    public GameEnvironment getGameEnv() {
        return this.gameN;
    }


    /**
     * Moves the ball one step, and when it hits the wall - changes its dx or dy,
     * and then its x or y respectively.
     */
    public void moveOneStep() {
        //save current velocity values
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        /*check for collisions if we would move the ball from its current point to the next one
        with the current velocity*/
        Point p = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, p);
        CollisionInfo col = this.getGameEnv().getClosestCollision(trajectory);
        //if there will be a collision, change the velocity
        if (col != null) {
            this.setVelocity(col.collisionObject().hit(this, col.collisionPoint(), this.getVelocity()));
            //if changing velocity will put the ball inside a block, change both dx and dy of original velocity
            Point p2 = this.getVelocity().applyToPoint(this.center);
            if (this.insideRect(p2)) {
                this.setVelocity(-dx, -dy);
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        } else { //if there wouldn't be a collision, move the ball to the next point with the current velocity
            //if ball will go outside the frame, change its velocity
            checkCorner(dx, dy);
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Check if ball will be inside a rectangle after implementing on it the velcoity change.
     *
     * @param p2 the p 2
     * @return the boolean
     */
    public boolean insideRect(Point p2) {
        List<Collidable> collidables = this.gameN.getCollidables();
        for (Collidable c : collidables) {
            if (p2.getX() > c.getCollisionRectangle().getLeftBrd().start().getX()
                    && p2.getX() < c.getCollisionRectangle().getRightBrd().start().getX()
                    && p2.getY() > c.getCollisionRectangle().getTopBrd().start().getY()
                    && p2.getY() < c.getCollisionRectangle().getBottomBrd().start().getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if ball passes the frame, and if so, change velocity accordingly.
     * If it passes frame from top or bottom, change dy
     * If it passes frame from right or left, change dx
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void checkCorner(double dx, double dy) {
        if (this.getY() < TOP_BORDER && dy < 0 || this.getY() > BOTTOM_BORDER && dy > 0) {
            this.setVelocity(dx, -dy);
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        if (this.getX() < LEFT_BORDER && dx < 0 || this.getX() > RIGHT_BORDER && dx > 0) {
            this.setVelocity(-dx, dy);
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove ball from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
