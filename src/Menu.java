/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {
    /**
     * Add selection.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return the status
     */
    T getStatus();

    /**
     * Name: addSubMenu.
     *
     * @param key     the key.
     * @param message the message.
     * @param subMenu the subMenu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}