import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;
    private Counter numOfLives;
    private int lvlCounter;
    private HighScoresTable highScoresTable;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar              the ar
     * @param ks              the ks
     * @param gui             the gui
     * @param highScoresTable the high scores table
     * @param file            the file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable highScoresTable, File file) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = new Counter(0);
        this.numOfLives = new Counter(7);
        this.highScoresTable = highScoresTable;
        this.file = file;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //initialize fields
        numOfLives = new Counter(7);
        score = new Counter(0);
        lvlCounter = levels.size();

        for (LevelInformation levelInfo : levels) {
            lvlCounter--;
            GameLevel level = new GameLevel(levelInfo, new GameEnvironment(),
                    this.ks, this.ar, gui, score, numOfLives);
            Counter ballCounter = level.getBallCounter();
            Counter blockCounter = level.getBlockCounter();
            level.initialize();

            // while level has more blocks and player has more lives
            while (numOfLives.getValue() > 0 && blockCounter.getValue() > 0) {
                if (level.isAnotherTurn()) {
                    if (numOfLives.getValue() == 1) {
                        this.numOfLives.decrease(1);
                        break;
                    }
                    level.setRunning(true);
                    level.createBalls();
                    this.numOfLives.decrease(1);
                    level.playOneTurn();
                } else {
                    level.playOneTurn();
                }
            }

            //win
            if (lvlCounter == 0 && blockCounter.getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndScreen(score.getValue(), true)));
                break;
            }

            //no more lives - loss
            if (numOfLives.getValue() == 0 && ballCounter.getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndScreen(score.getValue(), false)));
                break;
            }
        }
        //add score to high scores list if worthy
        if (this.highScoresTable.getRank(this.score.getValue()) <= highScoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            highScoresTable.add(new ScoreInfo(this.score.getValue(), name));
            try {
                highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.highScoresTable, ks, ar)));
    }
}