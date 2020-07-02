import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * The type Start game task.
 */
public class StartGameTask implements Task<Void> {

    private GameFlow game;
    private List<LevelInformation> levels;
    private String path;

    /**
     * Instantiates a new Start game task.
     *
     * @param game   the game
     * @param levels list of the level informations
     * @param path   the path
     */
    public StartGameTask(GameFlow game, List<LevelInformation> levels, String path) {
        this.game = game;
        this.levels = levels;
        this.path = path;
    }

    /**
     * setTheLevels.
     */
    public void setLevels() {
        InputStream is;
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        List<LevelInformation> levelInfoList = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            Reader br = new BufferedReader(new InputStreamReader(is));
            levelInfoList = levelSpecificationReader.fromReader(br);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.levels = levelInfoList;
    }
    /**
     * Run t.
     *
     * @return the t
     */
    @Override
    public Void run() {
        setLevels();
        game.runLevels(levels);
        return null;
    }
}
