import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int score;
    private boolean win;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param win   the win
     */
    public EndScreen(int score, boolean win) {
        this.stop = false;
        this.score = score;
        this.win = win;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + score, 32);
            d.drawText(250, 500, "Press space to continue", 30);
        } else {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
            d.drawText(250, 500, "Press space to continue", 30);
        }
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