/**
 * The type Point.
 */
public class Point {

    //fields
    private double x, y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance of this point to the other point.
     *
     * @param other the other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        CheckDouble cd = new CheckDouble();
        if (cd.checkDouble(this.x, other.x) && cd.checkDouble(this.y, other.y)) {
            return true;
        }
        return false;
    }

    // Return the x and y values of this point

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}