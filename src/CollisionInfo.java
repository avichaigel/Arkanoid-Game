/**
 * The type Collision info.
 */
public class CollisionInfo {

    //fields
    private Point p;
    private Collidable c;

    /**
     * Instantiates a new Collision info.
     *
     * @param p the point
     * @param c the collidable
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.c;
    }
}