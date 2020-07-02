import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 *
 // The CountdownAnimation will display the given gameScreen,
 // for numOfSeconds seconds, and on top of them it will show
 // a countdown from countFrom back to 1, where each number will
 // appear on the screen for (numOfSeconds / countFrom)
 // seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom = 3;
    private SpriteCollection gameScreen;
    private boolean stop = false;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.blue);
        if (this.countFrom <= 0.0) {
            this.stop = true;
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 30);
        }
        sleeper.sleepFor((int) this.numOfSeconds * 1000 / 3);
        this.countFrom--;
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }
}