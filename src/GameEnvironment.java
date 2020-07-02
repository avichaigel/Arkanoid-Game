import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    //fields
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * // add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int flag = 0;
        //check if there is any collidable on the trajectory
        for (Collidable c : collidables) {
            if (c.getCollisionRectangle().intersectionPoints(trajectory).size() > 0) {
                ++flag;
            }
        }
        // if there's no collision, return null
        if (flag == 0) {
            return null;
        }

        Collidable minCol = null;
        //find first collidable in the collection that intersects with the line
        for (Collidable c : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                minCol = c;
                break;
            }
        }
        //if there is a collision, compare the intersection points, to find closest one to trajectory's start
        for (Collidable c : collidables) {
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) == null) {
                continue;
            }
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()).distance(trajectory.start())
                    < trajectory.closestIntersectionToStartOfLine(minCol.getCollisionRectangle()).
                    distance(trajectory.start())) {
                minCol = c;
            }
        }
        Point minP = trajectory.closestIntersectionToStartOfLine(minCol.getCollisionRectangle());
        return new CollisionInfo(minP, minCol);
    }

    /**
     * Gets collidables list.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
}

