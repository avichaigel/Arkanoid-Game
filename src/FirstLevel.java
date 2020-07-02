import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type First level.
 */
public class FirstLevel implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Initial ball velocities list.
     * // The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> vel = new ArrayList<Velocity>();
        //Velocity v = Velocity.fromAngleAndSpeed(180, -6);
        Velocity v = new Velocity(0, -6);
        vel.add(v);
        return vel;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Paddle point int.
     *
     * @return the int
     */
    @Override
    public Point paddlePoint() {
        return new Point(350, 570);
    }

    /**
     * Level name string.
     * // the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Gets background.
     *
     * // Returns a sprite with the background of the level
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Background("first");
    }

    /**
     * Blocks list.
     *
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks1 = new ArrayList<Block>();
        blocks1.add(new Block(new Rectangle(new Point(380, 180), 30, 30), Color.red));
        return blocks1;
    }

    /**
     * Number of blocks to remove int.
     *
     * // Number of blocks that should be removed
     * // before the level is considered "cleared".
     * // This number should be <= blocks.size();
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
