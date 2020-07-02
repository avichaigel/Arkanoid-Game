import biuoop.KeyboardSensor;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;

    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     * @param ks                  the ks
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.ks = ks;
    }

    /**
     * Run t.
     *
     * @return the t
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}