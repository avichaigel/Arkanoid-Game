/**
 * The type Line.
 */
public class Line {

    //fields
    private Point start, end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors

    /**
     * Instantiates a new Line.
     *
     * @param start start point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param xA the x 1
     * @param yA the y 1
     * @param xB the x 2
     * @param yB the y 2
     */
    public Line(double xA, double yA, double xB, double yB) {
        this.start = new Point(xA, yA);
        this.end = new Point(xB, yB);
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return Math.sqrt((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX())
                + (this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY()));
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean, returns true if the lines intersect, false otherwise.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        double xA = this.start.getX(), xB = this.end.getX(), xC = other.start.getX(), xD = other.end.getX(),
                yA = this.start.getY(), yB = this.end.getY(), yC = other.start.getY(), yD = other.end.getY();
        Point p;
        CheckDouble cd = new CheckDouble();
        // if both lines vertical, return null
        if (Math.abs(xA - xB) < 0.00000000001 && Math.abs(xC - xD) < 0.00000000001) { //if xA == xB && xC == xD
            return null;
        }
        // check if first line vertical
        if (Math.abs(xA - xB) < 0.00000000001) {
            double a2 = (yD - yC) / (xD - xC);
            double b2 = yC - (a2 * xC);
            double y = (a2 * xA) + b2;
            double xE = adjustDbl(xA, 3);
            double yE = adjustDbl(y, 3);
            Point p3 = this.pointOnLines(other, xE, yE);
            if (p3 != null) {
                return p3;
            }
            p = this.pointOnLines(other, xA, y);
            return p;
        }
        // check if second line vertical
        if (Math.abs(xC - xD) < 0.00000000001) {
            double a1 = (yB - yA) / (xB - xA);
            double b1 = yA - (a1 * xA);
            double y = (a1 * xC) + b1;
            double xE = adjustDbl(xC, 3);
            double yE = adjustDbl(y, 3);
            Point p3 = this.pointOnLines(other, xE, yE);
            if (p3 != null) {
                return p3;
            }
            p = this.pointOnLines(other, xC, y);
            return p;
        }
        // calculate slope of the lines
        double a1 = (yB - yA) / (xB - xA);
        double a2 = (yD - yC) / (xD - xC);
        if (a1 == a2) {
            return null;
        }

        // the b of the axis and b of the lines
        double b1 = yA - (a1 * xA);
        double b2 = yC - (a2 * xC);
        // the x of the point
        double x = (b2 - b1) / (a1 - a2);
        // the y of the point
        double y = (a1 * x) + b1;
        double p1 = adjustDbl(x, 3);
        double p2 = adjustDbl(y, 3);
        // checks if the the point is on the lines
        p = this.pointOnLines(other, x, y);
        Point p3 = this.pointOnLines(other, p1, p2);
        if (p3 != null) {
            return p3;
        }
        return p;
    }


    /**
     * Adjust precision to double.
     *
     * @param number    the number
     * @param adjustDbl the adjust dbl
     * @return the double
     */
    public double adjustDbl(double number, int adjustDbl) {
        double power = Math.pow(10, adjustDbl);
        return Math.round(number * power) / power;
    }


    /**
     * Find out if given point is on both lines (this and other).
     *
     * @param other the other
     * @param x     the x
     * @param y     the y
     * @return the point
     */
    public Point pointOnLines(Line other, double x, double y) {
        double xA = this.start.getX(), xB = this.end.getX(), xC = other.start.getX(), xD = other.end.getX(),
                yA = this.start.getY(), yB = this.end.getY(), yC = other.start.getY(), yD = other.end.getY();
        CheckDouble cd = new CheckDouble();
        if (cd.checkDouble(x, Math.max(xA, xB)) && cd.checkDouble(Math.min(xA, xB), x)
                && cd.checkDouble(y, Math.max(yA, yB)) && cd.checkDouble(Math.min(yA, yB), y)
                && cd.checkDouble(x, Math.max(xC, xD)) && cd.checkDouble(Math.min(xC, xD), x)
                && cd.checkDouble(y, Math.max(yC, yD)) && cd.checkDouble(Math.min(yC, yD), y)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * Equals boolean. return true if the lines are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) || this.start.equals(other.end))
                && (this.end.equals(other.start) || this.end.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //if there is no intersection
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        } else if (rect.intersectionPoints(this).size() == 1) { //if there is only one intersection
            return rect.intersectionPoints(this).get(0);
        } else { //if there are two intersections, return the closest one to the start point of the line
            if (new Line(this.start, rect.intersectionPoints(this).get(0)).length()
                    > new Line(this.start, rect.intersectionPoints(this).get(1)).length()) {
                return rect.intersectionPoints(this).get(1);
            } else {
                return rect.intersectionPoints(this).get(0);
            }
        }

    }
}