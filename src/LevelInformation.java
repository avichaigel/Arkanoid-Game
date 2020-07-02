import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * // The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Paddle point int.
     *
     * @return the int
     */
    Point paddlePoint();

    /**
     * Level name string.
     * // the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * // Returns a sprite with the background of the level
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     // The Blocks that make up this level, each block contains
     // its size, color and location.
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     // Number of blocks that should be removed
     // before the level is considered "cleared".
     // This number should be <= blocks.size();
     * @return the int
     */
    int numberOfBlocksToRemove();
}