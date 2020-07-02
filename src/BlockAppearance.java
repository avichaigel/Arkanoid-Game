import biuoop.DrawSurface;

/**
 * The interface Block appearance.
 */
public interface BlockAppearance {
    /**
     * fill a rec with image.
     *
     * @param rec         the rec.
     * @param drawSurface the surface.
     */
    void fill(Rectangle rec, DrawSurface drawSurface);

    /**
     * Draw.
     *
     * @param rec         the rec
     * @param drawSurface the draw surface
     */
    void draw(Rectangle rec, DrawSurface drawSurface);
}
