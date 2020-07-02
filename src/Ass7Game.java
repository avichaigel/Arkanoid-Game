import biuoop.GUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    /**
     * Start the game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("game", 800, 600);
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        //if arguments were given, add to "levels" levels 1-4
        if (args.length != 0) {
            for (String s : args) {
                if (s.equals("1")) {
                    levels.add(new FirstLevel());
                } else if (s.equals("2")) {
                    levels.add(new SecondLevel());
                } else if (s.equals("3")) {
                    levels.add(new ThirdLevel());
                } else if (s.equals("4")) {
                    levels.add(new FourthLevel());
                }
            }
        }

        //if no arguments were given, play levels 1-4 by numerical order
        levels.add(new FirstLevel());
        levels.add(new SecondLevel());
        levels.add(new ThirdLevel());
        levels.add(new FourthLevel());

        //read high scores from file or create one if doesn't exist already
        File file = new File("highscores.txt");
        HighScoresTable highScoresTable = new HighScoresTable(5);
        try {
            if (!file.exists()) {
                highScoresTable.save(file);
            } else {
                highScoresTable.load(file);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        highScoresTable = HighScoresTable.loadFromFile(file);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        GameFlow game = new GameFlow(runner, gui.getKeyboardSensor(), gui, highScoresTable, file);

        InputStream input;
        Reader reader = null;
        Menu<Task<Void>> subMenu;
        try {
            if (args.length > 0) {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            } else {
                input = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
            reader = new InputStreamReader(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String s = BlocksDefinitionReader.getLines(reader);
        Pattern pattern = Pattern.compile("(.*):(.*)\n(.*)");
        Matcher m = pattern.matcher(s);
        subMenu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), runner);
        while (m.find()) {
            String path = m.group(3);
            subMenu.addSelection(m.group(1), m.group(2), new StartGameTask(game, levels, path));
        }

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), runner);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(runner,
                new HighScoresAnimation(highScoresTable, gui.getKeyboardSensor(), runner), gui.getKeyboardSensor()));
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("q", "Quit", new QuitTask());

        while (true) {
            ((MenuAnimation) menu).setStop(false);
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}