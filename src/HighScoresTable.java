import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The type High scores table.
 */
class HighScoresTable {

    private int size;
    private List scoreList;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>();
    }

    /**
     * Add a high-score.
     *
     * @param info the info
     */
    public void add(ScoreInfo info) {
        Iterator<ScoreInfo> scoreInfoIterator = scoreList.iterator();
        if (scoreList.size() >= this.size) {
            while (scoreInfoIterator.hasNext()) {
                if (info.getScore() >= scoreInfoIterator.next().getScore()) {
                    scoreList.add(info);
                    Collections.sort(scoreList);
                    scoreList.remove(this.size);
                    break;
                }
            }
        } else {
            scoreList.add(info);
        }
    }

    /**
     * // Return table size.
     *
     * @return the int
     */
    public int size() {
        return this.size;
    }

    /**
     * // Return the current high scores.
     * // The list is sorted such that the highest scores come first.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        Collections.sort(scoreList);
        return scoreList;
    }

    /**
     * // return the rank of the current score: where will it be on the list if added?
     * // Rank 1 means the score will be highest on the list.
     * // Rank `size` means the score will be lowest.
     * // Rank > `size` means the score is too low and will not
     * //      be added to the list.
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        Collections.sort(scoreList);
        int place = 1;
        if (scoreList.isEmpty()) {
            return 1;
        }

        for (int i = 0; i < scoreList.size(); i++) {
            if (score > getHighScores().get(i).getScore()) {
                return place;
            } else if (score == getHighScores().get(i).getScore() && place < this.size) {
                return place + 1;
            }
            place += 1;
        }
        if (place < this.size) {
            return place;
        }
            // if got here, that means rank > size, therefore will not be added to the list.
            return size + 1;
    }

    /*
    100
    80
     */

    /**
     * // Clears the table.
     */
    public void clear() {
        scoreList.removeAll(scoreList);
    }

    /**
     * // Load table data from file.
     * // Current table data is cleared.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        //this.clear();
        BufferedReader is = null;
        try {
            is = new BufferedReader(// wrapper that reads ahead
                    new InputStreamReader(// wrapper that reads characters
                            new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                String[] tok = line.split(":", 2);
                this.scoreList.add(new ScoreInfo(Integer.parseInt(tok[1]), tok[0]));
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }

    /**
     * // Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(// wrapper with many ways of writing strings
                    new OutputStreamWriter(// wrapper that can write strings
                            new FileOutputStream(filename)));
            for (int i = 0; i < getHighScores().size(); i++) {
                os.println(this.getHighScores().get(i).getName() + ":" + this.getHighScores().get(i).getScore());
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (os != null) { // Exception might have happened at constructor
                os.close(); // closes fileOutputStream too
            }
        }
    }

    /**
     * // Read a table from file and return it.
     * // If the file does not exist, or there is a problem with
     * // reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return the high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        BufferedReader is = null;
        List<String> arr = new ArrayList<>();
        int i = 0;
        try {
            is = new BufferedReader(// wrapper that reads ahead
                    new InputStreamReader(// wrapper that reads characters
                            new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                arr.add(line);
                i++;
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        HighScoresTable highScoresTable = new HighScoresTable(5);
        for (int j = 0; j < i; j++) {
            String[] tok = arr.get(j).split(":", 2);
            highScoresTable.add(new ScoreInfo(Integer.parseInt(tok[1]), tok[0]));
        }
        return highScoresTable;
    }
}