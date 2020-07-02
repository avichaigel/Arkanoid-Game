import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {

    private Rectangle paddle;
    private KeyboardSensor keyboard;
    private GUI gui;
    private Block[] boundaries;
    private int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard   the keyboard
     * @param paddle     the paddle
     * @param gui        the gui
     * @param boundaries the boundaries
     * @param speed      the speed
     */
//constructor
    public Paddle(KeyboardSensor keyboard, Rectangle paddle, GUI gui, Block[] boundaries, int speed) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.gui = gui;
        this.boundaries = boundaries;
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double curX = this.paddle.getUpperLeft().getX();
        double curY = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        this.paddle = new Rectangle(new Point(curX - speed, curY), width, height);
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double curX = this.paddle.getUpperLeft().getX();
        double curY = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        this.paddle = new Rectangle(new Point(curX + speed, curY), width, height);
    }

    /**
     * check if paddle touches bounds.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean touchesBounds(String key) {
        //paddle touches left border
        if (key.contentEquals("left")) {
            if (paddle.getLeftBrd().start().getX()
                    <= boundaries[1].getCollisionRectangle().getRightBrd().start().getX()) {
                return true;
            }
        } else if (key.contentEquals("right")) {  //paddle touches right border
            if (paddle.getRightBrd().start().getX()
                    >= boundaries[3].getCollisionRectangle().getLeftBrd().start().getX()) {
                return true;
            }
        }
        return false;
    }

    // Sprite

    /**
     * notify the sprite that time has passed.
     **/
    public void timePassed() {
        this.keyboard = gui.getKeyboardSensor();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && !touchesBounds(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && !touchesBounds(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw sprite on the screen.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) paddle.getUpperLeft().getX();
        int y = (int) paddle.getUpperLeft().getY();
        int width = (int) paddle.getWidth();
        int height = (int) paddle.getHeight();
        d.setColor(new Color(0xFDC701));
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
    }

    // Collidable

    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Notify the object that we collided with at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter         the ball that hits the object
     * @param collisionPoint the collision point
     * @param curVel         the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity curVel) {
        //split paddle to 5 segments
        double splitPad = this.paddle.getTopBrd().length() / 5;
        Point p1 = new Point(this.paddle.getUpperLeft().getX() + splitPad, this.paddle.getUpperLeft().getY());
        Point p2 = new Point(this.paddle.getUpperLeft().getX() + 2 * splitPad, this.paddle.getUpperLeft().getY());
        Point p3 = new Point(this.paddle.getUpperLeft().getX() + 3 * splitPad, this.paddle.getUpperLeft().getY());
        Point p4 = new Point(this.paddle.getUpperLeft().getX() + 4 * splitPad, this.paddle.getUpperLeft().getY());
        Point p5 = new Point(this.paddle.getUpperLeft().getX() + 5 * splitPad, this.paddle.getUpperLeft().getY());
        Line l1 = new Line(this.paddle.getUpperLeft(), p1);
        Line l2 = new Line(p1, p2);
        Line l3 = new Line(p2, p3);
        Line l4 = new Line(p3, p4);
        Line l5 = new Line(p4, p5);

        double speed1 = Math.sqrt(Math.pow(curVel.getDx(), 2) + Math.pow(curVel.getDy(), 2));

        //ball hits region 1 of the paddle (leftmost part) or left border of paddle
        if (this.pointOnLine(collisionPoint, l1) || this.pointOnLine(collisionPoint, this.paddle.getLeftBrd())) {
            return Velocity.fromAngleAndSpeed(300, speed1);
        } else if (this.pointOnLine(collisionPoint, l2)) { //region 2
            return Velocity.fromAngleAndSpeed(330, speed1);
        } else if (this.pointOnLine(collisionPoint, l3)) { //ball hits center of paddle
            return new Velocity(curVel.getDx(), -curVel.getDy());
        } else if (this.pointOnLine(collisionPoint, l4)) { //region 4
            return Velocity.fromAngleAndSpeed(30, speed1);
        } else if (this.pointOnLine(collisionPoint, l5)
                || this.pointOnLine(collisionPoint, this.paddle.getRightBrd())) { //region 5 of right border of paddle
            return Velocity.fromAngleAndSpeed(60, speed1);
        } else if (this.pointOnLine(collisionPoint, this.paddle.getBottomBrd())) {
            return new Velocity(-curVel.getDx(), curVel.getDy());
        } else { //doesn't hit paddle
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
     * Add this paddle to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}