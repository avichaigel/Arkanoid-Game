import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type First level.
 */
public class SecondLevel implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 10;
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
        //left hand half of the balls
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity v = new Velocity(-i - 1, -4);
            vel.add(v);
        }
        //right hand half of the balls
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity v = new Velocity(i + 1, -4);
            vel.add(v);
        }
        return vel;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 3;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 600;
    }

    /**
     * Paddle point int.
     *
     * @return the int
     */
    @Override
    public Point paddlePoint() {
        return new Point(100, 570);
    }

    /**
     * Level name string.
     * // the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
        return new Background("second");
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
        for (int i = 0; i < 14; i++) {
            blocks1.add(new Block(new Rectangle(new Point(1 + (i * 53), 250), 53, 25), color(i)));
        }
        //last block, which is a tad wider
        blocks1.add(new Block(new Rectangle(new Point(14 * 53, 250), 57, 25), Color.cyan));
        return blocks1;
    }

    /**
     * Color color.
     *
     * @param i the
     * @return the color
     */
    private Color color(int i) {
        if (i < 2) {
            return Color.red;
        } else if (i == 2 || i == 3) {
            return Color.orange;
        } else if (i == 4 || i == 5) {
            return Color.yellow;
        } else if (i == 6 || i == 7 || i == 8) {
            return Color.green;
        } else if (i == 9 || i == 10) {
            return Color.blue;
        } else if (i == 11 || i == 12) {
            return Color.pink;
        } else { //if (i==13)
            return Color.cyan;
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
