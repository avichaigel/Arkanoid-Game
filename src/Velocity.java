import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * The type Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    //field
    /**
     * the dx and the dy, meanning the change in x and y.
     */
    private double dx, dy;

    /**
     * Instantiates a new Velocity from angle and speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = toRadians(angle - 90);
        double dx = (cos(angle) * speed);
        double dy = (sin(angle) * speed);
        return new Velocity(dx, dy);
    }
    // constructor

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Apply to point point.
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     *
     * @param p the old point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}