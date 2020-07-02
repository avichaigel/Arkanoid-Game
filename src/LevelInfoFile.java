import java.awt.Image;
import java.awt.Color;
import java.util.List;

/**
 * LevelInformationFile Class.
 */
public class LevelInfoFile implements LevelInformation {
    private String levelName;
    private int numberOfBalls;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private int blockNum;
    private int paddleSpeed;
    private int paddleWidth;
    private Color color;
    private Image image;

    /**
     * LevelInformationFile.
     *
     * @param levelName     levelName.
     * @param numberOfBalls balls.
     * @param velocities    velocities.
     * @param blocks        blocks.
     * @param blockNum     num of blocks.
     * @param paddleSpeed   speed.
     * @param paddleWidth   width.
     */
    public LevelInfoFile(String levelName, int numberOfBalls, List<Velocity> velocities, List<Block> blocks,
                                int blockNum, int paddleSpeed, int paddleWidth) {
        this.levelName = levelName;
        this.numberOfBalls = numberOfBalls;
        this.velocities = velocities;
        this.blocks = blocks;
        this.blockNum = blockNum;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
    }

    /**
     * setFunc.
     * @param image1 the image.
     */
    public void setImage(Image image1) {
        this.image = image1;
    }

    /**
     * set Func.
     * @param color1 the color.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * Name: numberOfBalls.
     *
     * @return the num.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Name: initialBallVelocities.
     *
     * @return the velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * Name: paddleSpeed.
     *
     * @return the speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Name: paddleWidth.
     *
     * @return the width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
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
     * Name: levelName.
     *
     * @return the name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Name: getBackground.
     *
     * @return the sprite.
     */
    public Sprite getBackground() {
        return new Background(this.color, this.image, 800, 600);
    }

    /**
     * Name: blocks.
     *
     * @return the blocks array.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Name: numberOfBlocksToRemove.
     *
     * @return the num.
     */
    public int numberOfBlocksToRemove() {
        return this.blockNum;
    }
}
