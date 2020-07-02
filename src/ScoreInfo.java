/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo> {

    private int score;
    private String name;

    /**
     * Instantiates a new Score info.
     *
     * @param score the score
     * @param name  the name
     */
    public ScoreInfo(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ScoreInfo o) {
        if (o.score == this.score) {
            return 0;
        } else if (o.score > this.score) {
            return 1;
        } else { //if (o.score < this.score)
            return -1;
        }
    }
}