import java.awt.Color;

/**
 * ColorParser Class.
 */
public class ColorsParser {
    /**
     * colorFromString.
     *
     * @param s the string.
     * @return the color.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            int ind1 = s.indexOf("(") + 1;
            int ind2 = s.indexOf(")");
            String rgb = s.substring(ind1, ind2);
            String[] rgbs = rgb.split(",");
            return new Color(Integer.parseInt(rgbs[0]), Integer.parseInt(rgbs[1]), Integer.parseInt(rgbs[2]));
        } else {
            int ind1 = s.indexOf("color(") + 6;
            int ind2 = s.indexOf(")");
            String color = s.substring(ind1, ind2);
            switch (color) {
                case "blue":
                    return Color.BLUE;
                case "black":
                    return Color.BLACK;
                case "green":
                    return Color.green;
                case "white":
                    return Color.white;
                case "red":
                    return Color.red;
                case "yellow":
                    return Color.yellow;
                case "cyan":
                    return Color.cyan;
                case "orange":
                    return Color.orange;
                case "pink":
                    return Color.pink;
                case "gray":
                    return Color.gray;
                case "lightGray":
                    return Color.lightGray;
                default:
                    return null;
            }
        }
    }
}