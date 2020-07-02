import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;
    private boolean stop = false;
    private KeyboardSensor ks;
    private AnimationRunner runner;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param ks     the keyboardsensor
     * @param runner the runner
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor ks, AnimationRunner runner) {
        this.ks = ks;
        this.scores = scores;
        this.runner = runner;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0x1787CF));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0x813541));
        d.drawText(202, 127, "Player Name", 32);
        d.drawText(602, 127, "Score", 32);
        d.setColor(new Color(0xC5455A));
        d.drawText(200, 125, "Player Name", 32);
        d.drawText(600, 125, "Score", 32);
        d.setColor(new Color(0x813541));
        d.drawOval(210, 150, 450, 3);
        d.setColor(new Color(0xC5455A));
        d.fillOval(210, 150, 450, 3);
        d.setColor(new Color(0x0E0740));
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            ScoreInfo s = this.scores.getHighScores().get(i);
            d.drawText(200, 200 + (40 * i), s.getName(), 30);
            d.drawText(600, 200 + (40 * i), String.valueOf(s.getScore()), 30);
        }
        d.drawText(175, 500, "Press space to go back to main menu", 30);
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}