package hL.easy.lang.simulation.commons.lang;

import hL.easy.lang.commons.Classes;
import java.lang.reflect.Array;

/**
 * @author @HL
 * @since 0.1.001
 */
public class ArrayUtils {

    public static final String SIMULATED_1 = "org.apache.commons.lang3.ArrayUtils";
    public static final String SIMULATED_2 = "org.apache.commons.lang.ArrayUtils";
    public static final boolean EXISTS_SIMULATED_1;
    public static final boolean EXISTS_SIMULATED_2;

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
        EXISTS_SIMULATED_2 = Classes.isExists(SIMULATED_2);
    }

    public static int getLength(final Object array) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.ArrayUtils.getLength(array);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.ArrayUtils.getLength(array);
        } else {
            if (array == null) {
                return 0;
            }
            return Array.getLength(array);
        }
    }

    public static boolean isEmpty(final Object[] array) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.ArrayUtils.isEmpty(array);
        } else {
            return getLength(array) == 0;
        }
    }
}
