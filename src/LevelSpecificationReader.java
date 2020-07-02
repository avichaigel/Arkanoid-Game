import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LevelSpecificationReader Class.
 */
public class LevelSpecificationReader {
    /**
     * fromReader.
     *
     * @param reader the reader.
     * @return the list.
     * @throws Exception exp.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInfos = new ArrayList<>();
        List<String> levels = getLevelsList(reader);
        String levelName = null;
        String blockPattern = null;
        Image image = null;
        Color color = null;
        String blocksPath = null;
        String background;
        Integer ballNum;
        Integer blocksStartX = null;
        Integer blocksStartY = null;
        Integer rowHeight = null;
        List<Velocity> velocities = null;
        List<Block> blocks = new ArrayList<>();
        Integer blockNum = null;
        Integer paddleSpeed = null;
        Integer paddleWidth = null;
        try {
            for (int i = 0; i < levels.size(); i++) {
                blocks = new ArrayList<>();
                Pattern p = Pattern.compile("level_name:(.*)");
                Matcher m = p.matcher(levels.get(i));
                if (m.find()) {
                    levelName = m.group(1);
                }
                p = Pattern.compile("ball_velocities:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    velocities = getVelocities(m.group(1));
                }
                p = Pattern.compile("paddle_speed:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    paddleSpeed = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("blocks_start_x:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    blocksStartX = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("blocks_start_y:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    blocksStartY = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("row_height:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    rowHeight = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("paddle_width:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    paddleWidth = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("num_blocks:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    blockNum = Integer.parseInt(m.group(1));
                }
                p = Pattern.compile("block_definitions:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    blocksPath = m.group(1);
                }
                p = Pattern.compile("background:(.*)");
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    background = m.group(1);
                    if (background.contains("image")) {
                        int indexOfStart = background.indexOf("(");
                        int indexOfEnd = background.indexOf(")");
                        String backgroundPath = background.substring(indexOfStart + 1, indexOfEnd);
                        try {
                            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(backgroundPath);
                            BufferedImage im = ImageIO.read(is);
                            image = im;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (background.contains("color")) {
                        if (background.contains(("RGB"))) {
                            int index1 = background.indexOf("(");
                            int index2 = background.indexOf(")");
                            String path = background.substring(index1 + 1, index2 + 1);
                            ColorsParser colorsParser = new ColorsParser();
                            color = colorsParser.colorFromString(path);
                        } else {
                            ColorsParser colorsParser = new ColorsParser();
                            color = colorsParser.colorFromString(background);
                        }
                    }
                }
                ballNum = velocities.size();
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blocksPath);
                BlocksFromSymbolsFactory bf = BlocksDefinitionReader.fromReader(new BufferedReader(
                        new InputStreamReader(is)));
                p = Pattern.compile("START_BLOCKS(.*?)END_BLOCKS", Pattern.MULTILINE | Pattern.DOTALL);
                m = p.matcher(levels.get(i));
                if (m.find()) {
                    blockPattern = m.group(1);
                }
                String[] blocksPatternSplitted = blockPattern.split("\n");
                int xPos = blocksStartX;
                int yPos = blocksStartY;
                for (int j = 0; j < blocksPatternSplitted.length; j++) {
                    for (int h = 0; h < blocksPatternSplitted[j].length(); h++) {
                        String s = Character.toString(blocksPatternSplitted[j].charAt(h));
                        if (bf.isSpaceSymbol(s)) {
                            xPos += bf.getSpaceWidth(s);
                        } else if (bf.isBlockSymbol(s)) {
                            Block d = bf.getBlock(s, xPos, yPos);
                            blocks.add(d);
                            xPos += d.getCollisionRectangle().getWidth();
                        }
                    }
                    yPos += rowHeight;
                    xPos = blocksStartX;
                }
                LevelInfoFile levelInformationFile = new LevelInfoFile(levelName, ballNum,
                        velocities, blocks, blockNum, paddleSpeed, paddleWidth);
                levelInformationFile.setColor(color);
                levelInformationFile.setImage(image);
                levelInfos.add(levelInformationFile);
                color = null;
            }
        } catch (Exception e) {
            System.out.println("Exception was caught");
            System.exit(1);
        }
        return levelInfos;
    }

    /**
     * getVelocities.
     *
     * @param s the string.
     * @return the list.
     */
    public List<Velocity> getVelocities(String s) {
        String[] vel = s.split(" ");
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < vel.length; i++) {
            String[] velo = vel[i].split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(velo[0]), Double.parseDouble(velo[1])));
        }
        return velocities;
    }

    /**
     * getLevelsList.
     *
     * @param reader the reader.
     * @return the list.
     */
    public List<String> getLevelsList(java.io.Reader reader) {
        String sb = getLines(reader);
        List<String> levels = new ArrayList<>();
        Pattern p = Pattern.compile("START_LEVEL(.*?)END_LEVEL", Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = p.matcher(sb);
        while (m.find()) {
            levels.add(m.group(1));
        }
        return levels;
    }

    /**
     * getLines.
     *
     * @param reader the reader.
     * @return the String.
     */
    public String getLines(java.io.Reader reader) {
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
}