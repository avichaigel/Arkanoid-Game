/**
 * The type Check double.
 */
public class CheckDouble {

    /**
     * compare two doubles.
     *
     * @param a the a double
     * @param b the b double
     * @return true if b > a or the absolute value of the difference between a and b is smaller than
     * 0.0000000000001
     */
    public boolean checkDouble(double a, double b) {
        if (Math.abs(a - b) < 0.00000000001 || b > a) {
            return true;
        }
        return false;
    }
}
