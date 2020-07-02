import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation lvlInfo;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter numOfLives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private boolean anotherTurn;

    /**
     * Instantiates a new Game.
     *
     * @param lvlInfo     the level information
     * @param environment the environment
     * @param ks          the ks
     * @param ar          the ar
     * @param gui         the gui
     * @param score       the score
     * @param numOfLives  the num of lives
     */
    public GameLevel(LevelInformation lvlInfo, GameEnvironment environment, KeyboardSensor ks,
                     AnimationRunner ar, GUI gui, Counter score, Counter numOfLives) {
        this.sprites = new SpriteCollection();
        this.environment = environment;
        this.lvlInfo = lvlInfo;
        this.gui = gui;
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(0);
        this.score = score;
        this.numOfLives = numOfLives;
        this.runner = ar;
        this.keyboard = ks;
        this.running = true;
        this.anotherTurn = false;
    }

    /**
     * Add collidable to game environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add sprite to game environment.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Is another turn boolean.
     *
     * @return the boolean
     */
    public boolean isAnotherTurn() {
        return anotherTurn;
    }

    /**
     * Sets running.
     *
     * @param runn the running
     */
    public void setRunning(boolean runn) {
        this.running = runn;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {

        sprites.addSprite(lvlInfo.getBackground());
        //create balls
        this.createBalls();

        //create blocks by rows starting from top to bottom
        this.createBlocks();

        //create boundaries of the frame
        Block[] boundaries = new Block[4];
        boundaries[0] = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.black);
        boundaries[1] = new Block(new Rectangle(new Point(0, 20), 0, 580), Color.BLACK);
        boundaries[2] = new Block(new Rectangle(new Point(0, 600), 800, 1), Color.BLACK);
        boundaries[3] = new Block(new Rectangle(new Point(800, 20), 1, 580), Color.BLACK);
        for (Block bl : boundaries) {
            bl.addToGame(this);
        }
        BallRemover ballListener = new BallRemover(this, ballCounter);
        boundaries[2].addHitListener(ballListener);

        //create paddle
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Rectangle(lvlInfo.paddlePoint(), lvlInfo.paddleWidth(), 15),
                gui, boundaries, lvlInfo.paddleSpeed());
        paddle.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        LivesIndicator livesIndicator = new LivesIndicator(this.numOfLives);
        livesIndicator.addToGame(this);

        LevelIndicator levelIndicator = new LevelIndicator(lvlInfo.levelName());
        levelIndicator.addToGame(this);
    }

    /**
     * Create balls.
     */
    public void createBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        for (int i = 0; i < lvlInfo.numberOfBalls(); i++) {
            balls.add(new Ball(400, 540, 6, Color.white));
            balls.get(i).setVelocity(lvlInfo.initialBallVelocities().get(i));
        }
        for (Ball b : balls) {
            b.setGameEnv(environment);
            b.addToGame(this);
            ballCounter.increase(1);
        }
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        BlockRemover blockListener = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (Block bl : lvlInfo.blocks()) {
            bl.addToGame(this);
            bl.addHitListener(blockListener);
            bl.addHitListener(scoreTrackingListener);
            this.blockCounter.increase(1);
        }
    }

    /**
     * Play one turn.
     */
    public void playOneTurn() {
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts
        // use our runner to run the current animation -- which is one turn of the game
        this.runner.run(this);
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        } else if (ballCounter.getValue() == 0) {
            this.running = false;
/*            if (this.numOfLives.getValue() == 1) {
                anotherTurn = false;
                return;
            }*/
            anotherTurn = true;
            return;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
}