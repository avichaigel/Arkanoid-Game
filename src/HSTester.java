import java.util.List;

/**
 * The type Hs tester.
 */
public class HSTester {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {



        HighScoresTable highscores = new HighScoresTable(10);
        ScoreInfo a = new ScoreInfo(12, "avi");
        ScoreInfo b = new ScoreInfo(194, "manu");
        ScoreInfo c = new ScoreInfo(24, "gel");
        ScoreInfo d = new ScoreInfo(234, "coh");
        highscores.add(a);
        highscores.add(b);
        highscores.add(c);
        highscores.add(d);
        for (int i = 0; i < 12; i++) {
            ScoreInfo s = new ScoreInfo(i, "a" + String.valueOf(i));
            highscores.add(s);
        }




        List<ScoreInfo> list = highscores.getHighScores();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + "," + list.get(i).getScore());
        }

        System.out.println(highscores.getRank(43));
        System.out.println(highscores.getRank(-1));

        highscores.clear();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + "," + list.get(i).getScore());
        }

        System.out.println(highscores.getRank(43));
    }
}
