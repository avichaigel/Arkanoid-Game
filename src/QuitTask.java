/**
 * The type Quit task.
 */
public class QuitTask implements Task<Void> {

    /**
     * Instantiates a new Quit task.
     */
    public QuitTask() {
    }

    /**
     * Run t.
     *
     * @return the t
     */
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
