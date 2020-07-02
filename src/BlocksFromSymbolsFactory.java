import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksFromSymbolsFactory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }

    /**
     * isSpace.
     *
     * @param s the spacer.
     * @return bool. boolean
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * isBlock.
     *
     * @param s the block.
     * @return the bool.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * getBlock.
     *
     * @param s    the s.
     * @param xpos the pos.
     * @param ypos the pos.
     * @return the Block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * geySpace.
     *
     * @param s the one to get.
     * @return the int.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * addToBlocks.
     *
     * @param symbol         the sym.
     * @param blockGenerator the creator.
     */
    public void addToBlocks(String symbol, BlockCreator blockGenerator) {
        this.blockCreators.put(symbol, blockGenerator);
    }

    /**
     * addToSpace.
     *
     * @param symbol the symbol.
     * @param i      the integer.
     */
    public void addToSpace(String symbol, Integer i) {
        this.spacerWidths.put(symbol, i);
    }
}