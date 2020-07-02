import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type First level.
 */
public class ThirdLevel implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 2;
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
        Velocity v = new Velocity(5, -6);
        vel.add(v);
        Velocity v2 = new Velocity(-5, -6);
        vel.add(v2);
        return vel;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 12;
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
        return "Green 3";
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
        return new Background("third");
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
        //create blocks by rows starting from top to bottom
        this.createBlocks(10, 50, Color.gray, blocks1);
        this.createBlocks(9, 75, Color.red, blocks1);
        this.createBlocks(8, 100, Color.yellow, blocks1);
        this.createBlocks(7, 125, Color.blue, blocks1);
        this.createBlocks(6, 150, Color.white, blocks1);
        return blocks1;
    }

    /**
     * Create blocks.
     *
     * @param max     the max
     * @param y       the y
     * @param color   the color
     * @param blocks1 the blocks 1
     */
    public void createBlocks(int max, int y, Color color, List<Block> blocks1) {
        for (int i = 0; i < max; i++) {
            Block b = new Block(new Rectangle(new Point(750 - (i * 50), y + 50), 50, 25), color);
            blocks1.add(b);
        }
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
