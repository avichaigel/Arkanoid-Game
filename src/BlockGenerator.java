import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;


/**
 * BlockGenerator Class.
 */
public class BlockGenerator implements BlockCreator {
    private Color stroke;
    private String symbol;
    private Map<Integer, BlockAppearance> blockAppearanceList;
    private int hits;
    private int width;
    private int height;


    /**
     * Constructor.
     */
    public BlockGenerator() {
        this.blockAppearanceList = new TreeMap<>();
    }

    /**
     * create.
     *
     * @param x the x.
     * @param y the y.
     * @return block.
     */
    public Block create(int x, int y) {
        return new Block(new Rectangle(new Point(x, y), width, height), blockAppearanceList, hits, stroke);
    }

    /**
     * set func.
     *
     * @param blockAppearanceMap1 the app.
     */
    public void setBlockAppearanceMap(Map<Integer, BlockAppearance> blockAppearanceMap1) {
        this.blockAppearanceList = blockAppearanceMap1;
    }

    /**
     * get func.
     *
     * @return the symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * set func.
     *
     * @param symbol1 the sym.
     */
    public void setSymbol(String symbol1) {
        this.symbol = symbol1;
    }

    /**
     * set func.
     *
     * @param width1 the width.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * set func.
     *
     * @param hits1 the hits.
     */
    public void setHitPoints(int hits1) {
        this.hits = hits1;
    }

    /**
     * set func.
     *
     * @param height1 the height.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * set func.
     *
     * @param stroke1 the stroke.
     */
    public void setStroke(Color stroke1) {
        this.stroke = stroke1;
    }
}
