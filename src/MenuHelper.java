/**
 * The type Menu helper.
 *
 * @param <T> the type parameter
 */
public class MenuHelper<T> {

    private String key;
    private String message;
    private T returnVal;

    /**
     * Instantiates a new Menu helper.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public MenuHelper(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets return val.
     *
     * @return the return val
     */
    public T getReturnVal() {
        return returnVal;
    }
}
