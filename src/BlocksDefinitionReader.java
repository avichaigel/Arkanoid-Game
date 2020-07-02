import java.awt.Color;
import java.io.BufferedReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * BlockReader Class.
 */
public class BlocksDefinitionReader {
    /**
     * from reader.
     *
     * @param reader the file.
     * @return the factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory symbolsFactory = new BlocksFromSymbolsFactory();
        String symbolDef = null, symbol = null, lines = getLines(reader);
        String[] linesSplitted = lines.split("\n");
        Integer hitsDef = null, hits = null, widthDef = null, width = null, heightDef = null, height = null;
        Color strokeDef = null, stroke = null;
        Map<Integer, BlockAppearance> blockAppearanceMap = new TreeMap<>();
        Map<Integer, BlockAppearance> blockAppearanceMapDef = new TreeMap<>();
        try {
            for (int i = 0; i < linesSplitted.length; i++) {
                if (!(linesSplitted[i].startsWith("#") || linesSplitted[i].equals(""))) {
                    if (linesSplitted[i].contains("default")) {
                        String[] splitTheLine = linesSplitted[i].split(" ");
                        if (splitTheLine[0].contains("default")) {
                            for (int j = 0; j < splitTheLine.length; j++) {
                                if (splitTheLine[j].contains("symbol")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    symbolDef = splitSplit[1];
                                    symbol = symbolDef;
                                }
                                if (splitTheLine[j].contains("hit_points")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    hitsDef = Integer.parseInt(splitSplit[1]);
                                    hits = hitsDef;
                                }
                                if (splitTheLine[j].contains("fill")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    String almostPath = "";
                                    Integer key = -1;
                                    if (!splitSplit[0].equals("fill") && splitSplit[0].equals("fill-1")) {
                                        almostPath = splitSplit[1];
                                        key = Integer.parseInt(splitSplit[0].split("-")[1]);
                                    } else {
                                        key = 1;
                                    }
                                    if (almostPath.contains("image")) {
                                        int index1 = almostPath.indexOf("(");
                                        int index2 = almostPath.indexOf(")");
                                        String path = almostPath.substring(index1 + 1, index2);
                                        blockAppearanceMapDef.put(key, new UseImageBl(path));
                                        blockAppearanceMap.put(key, new UseImageBl(path));
                                    } else if (almostPath.contains("color")) {
                                        if (almostPath.contains(("RGB"))) {
                                            int index1 = almostPath.indexOf("(");
                                            int index2 = almostPath.indexOf(")");
                                            String path = almostPath.substring(index1 + 1, index2 + 1);
                                            Color color1 = new ColorsParser().colorFromString(path);
                                            blockAppearanceMap.put(key, new UseColorBl(color1));
                                        } else {
                                            Color color1 = new ColorsParser().colorFromString(almostPath);
                                            blockAppearanceMap.put(key, new UseColorBl(color1));
                                        }
                                    }
                                }
                                if (splitTheLine[j].contains("width")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    widthDef = Integer.parseInt(splitSplit[1]);
                                    width = widthDef;
                                }
                                if (splitTheLine[j].contains("height")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    heightDef = Integer.parseInt(splitSplit[1]);
                                    height = heightDef;
                                }
                                if (splitTheLine[j].contains("stroke")) {
                                    String[] splitSplit = splitTheLine[j].split(":");
                                    strokeDef = new ColorsParser().colorFromString(splitSplit[1]);
                                    stroke = strokeDef;
                                }
                            }
                        }
                    }
                    if (linesSplitted[i].contains("sdef")) {
                        String[] lineSplited = linesSplitted[i].split(" ");
                        String[] symbol1 = lineSplited[1].split(":");
                        String[] width1 = lineSplited[2].split(":");
                        symbolsFactory.addToSpace(symbol1[1], Integer.parseInt(width1[1]));
                    } else if (linesSplitted[i].contains("bdef")) {
                        String[] lineSplitted = linesSplitted[i].split(" ");
                        for (int j = 0; j < lineSplitted.length; j++) {
                            if (lineSplitted[j].contains("symbol")) {
                                String[] splitSplit = lineSplitted[j].split(":");
                                symbol = splitSplit[1];
                            }
                            if (lineSplitted[j].contains("hit_points")) {
                                String[] splitSplit = lineSplitted[j].split(":");
                                hits = Integer.parseInt(splitSplit[1]);
                            }
                            if (lineSplitted[j].contains("fill")) {
                                String[] splitSplit = lineSplitted[j].split(":");
                                String almostPath = "";
                                Integer key = -1;
                                if (!(splitSplit[0].equals("fill") || splitSplit[0].equals("fill-1"))) {
                                    almostPath = splitSplit[1];
                                    key = Integer.parseInt(splitSplit[0].split("-")[1]);
                                } else {
                                    almostPath = splitSplit[1];
                                    key = 1;
                                }
                                if (almostPath.contains("image")) {
                                    int index1 = almostPath.indexOf("(");
                                    int index2 = almostPath.indexOf(")");
                                    String path = almostPath.substring(index1 + 1, index2);
                                    blockAppearanceMap.put(key, new UseImageBl(path));
                                } else if (almostPath.contains("color")) {
                                    if (almostPath.contains(("RGB"))) {
                                        int index1 = almostPath.indexOf("(");
                                        int index2 = almostPath.indexOf(")");
                                        String path = almostPath.substring(index1 + 1, index2 + 1);
                                        ColorsParser colorsParser = new ColorsParser();
                                        Color color1 = colorsParser.colorFromString(path);
                                        blockAppearanceMap.put(key, new UseColorBl(color1));
                                    } else {
                                        ColorsParser colorsParser = new ColorsParser();
                                        Color color1 = colorsParser.colorFromString(almostPath);
                                        blockAppearanceMap.put(key, new UseColorBl(color1));
                                    }
                                }
                            }
                            if (lineSplitted[j].contains("width")) {
                                String[] splitSplit = lineSplitted[j].split(":");
                                width = Integer.parseInt(splitSplit[1]);
                            }
                            if (lineSplitted[j].contains("height")) {
                                String[] splitSplit = lineSplitted[j].split(":");
                                height = Integer.parseInt(splitSplit[1]);
                            }
                            if (lineSplitted[j].contains("stroke")) {
                                ColorsParser colorsParser = new ColorsParser();
                                String[] splitSplit = lineSplitted[j].split(":");
                                stroke = colorsParser.colorFromString(splitSplit[1]);
                            }
                        }
                        BlockGenerator bg = setBlock(symbol, hits, width, height, stroke, blockAppearanceMap);
                        symbolsFactory.addToBlocks(symbol, bg);
                        height = heightDef;
                        symbol = symbolDef;
                        hits = hitsDef;
                        width = widthDef;
                        stroke = strokeDef;
                        blockAppearanceMap = copyMap(blockAppearanceMapDef);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception was caught");
            System.exit(1);
        }
        return symbolsFactory;
    }

    /**
     * setBlock.
     *
     * @param symbol             sym.
     * @param hits          hp.
     * @param width              width.
     * @param height             height.
     * @param stroke             stroke.
     * @param blockAppearanceMap the map.
     * @return the block.
     */
    public static BlockGenerator setBlock(String symbol, Integer hits, Integer width, Integer height,
                                          Color stroke, Map<Integer, BlockAppearance> blockAppearanceMap) {
        BlockGenerator blockGenerator = new BlockGenerator();
        blockGenerator.setHitPoints(hits);
        blockGenerator.setSymbol(symbol);
        blockGenerator.setBlockAppearanceMap(blockAppearanceMap);
        blockGenerator.setHeight(height);
        blockGenerator.setWidth(width);
        blockGenerator.setStroke(stroke);
        
        return blockGenerator;
    }

    /**
     * getLines.
     *
     * @param reader the file.
     * @return the lines.
     */
    public static String getLines(java.io.Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            ;
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                ;
            }
        }
        return null;
    }

    /**
     * copyMap.
     *
     * @param blockAppearanceMap the map.
     * @return new map.
     */
    public static Map copyMap(Map<Integer, BlockAppearance> blockAppearanceMap) {
        Map<Integer, BlockAppearance> blockAppearanceMap2 = new TreeMap<>();
        for (Integer i : blockAppearanceMap.keySet()) {
            blockAppearanceMap2.put(i, blockAppearanceMap.get(i));
        }
        return blockAppearanceMap2;
    }
}