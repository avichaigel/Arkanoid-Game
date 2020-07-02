import java.util.LinkedList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {

    //members
    private Line topBrd;
    private Line bottomBrd;
    private Line leftBrd;
    private Line rightBrd;
    private Point upperLeft;
    private Point lowerLeft;
    private Point upperRight;
    private Point lowerRight;
    private double width;
    private double height;


    /**
     * Instantiates a new Rectangle, with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;

        this.upperLeft = upperLeft;
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());

        this.topBrd = new Line(upperLeft, upperRight);
        this.bottomBrd = new Line(lowerLeft, lowerRight);
        this.leftBrd = new Line(upperLeft, lowerLeft);
        this.rightBrd = new Line(upperRight, lowerRight);
    }

    /**
     * Return a (possibly empty) List of intersection point with the specified line.
     * Create a temporary list with all four lines of the rectangle, then check intersection of line argument with each
     *
     * @param line the line
     * @return the list
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> interList = new LinkedList<>();
        List<Line> temp = new LinkedList<>();
        temp.add(topBrd); // top line
        temp.add(bottomBrd); // bottom line
        temp.add(rightBrd); // right line
        temp.add(leftBrd); // left line
        //check intersection of line argument with each of the rectangle's lines
        for (Line l : temp) {
            if (line.isIntersecting(l)) {
                interList.add(line.intersectionWith(l));
            }
        }
        return interList;
    }

    // Return the width and height of the rectangle
    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets top border.
     *
     * @return the top border
     */
    public Line getTopBrd() {
        return topBrd;
    }

    /**
     * Gets bottom border.
     *
     * @return the bottom border
     */
    public Line getBottomBrd() {
        return bottomBrd;
    }

    /**
     * Gets left border.
     *
     * @return the left border
     */
    public Line getLeftBrd() {
        return leftBrd;
    }

    /**
     * Gets right border.
     *
     * @return the right border
     */
    public Line getRightBrd() {
        return rightBrd;
    }
}