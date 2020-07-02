import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type First level.
 */
public class FourthLevel implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 3;
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
        Velocity v = Velocity.fromAngleAndSpeed(181, -7);
        Velocity v1 = new Velocity(7, -7);
        Velocity v2 = new Velocity(-7, -7);
        vel.add(v);
        vel.add(v1);
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
        return "Final Four";
    }

    /**
     * Gets background.
     * <p>
     * // Returns a sprite with the background of the level
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Background("fourth");
    }

    /**
     * Blocks list.
     * <p>
     * // The Blocks that make up this level, each block contains
     * // its size, color and location.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks1 = new ArrayList<Block>();
        //create blocks by rows starting from top to bottom
        this.createBlocks(50, Color.darkGray, blocks1);
        this.createBlocks(75, Color.red, blocks1);
        this.createBlocks(100, Color.yellow, blocks1);
        this.createBlocks(125, Color.green, blocks1);
        this.createBlocks(150, Color.white, blocks1);
        this.createBlocks(175, Color.pink, blocks1);
        this.createBlocks(200, Color.cyan, blocks1);
        return blocks1;
    }

    /**
     * Create blocks.
     *
     * @param y       the y
     * @param color   the color
     * @param blocks1 the blocks 1
     */
    private void createBlocks(int y, Color color, List<Block> blocks1) {
        for (int i = 0; i < 16; i++) {
            Block b = new Block(new Rectangle(new Point(750 - (i * 50), y + 50), 50, 25), color);
            blocks1.add(b);
        }
    }

    /**
     * Number of blocks to remove int.
     * <p>
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
