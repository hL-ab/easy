package hL.easy.lang.simulation.commons.collection;

import hL.easy.lang.commons.Classes;
import java.util.Collection;

/**
 * @author @HL
 * @since 0.1.001
 */
public class CollectionUtils {

    public static final String SIMULATED_1 = "org.apache.commons.collections4.CollectionUtils";
    public static final String SIMULATED_2 = "org.apache.commons.collections.CollectionUtils";
    public static final boolean EXISTS_SIMULATED_1;
    public static final boolean EXISTS_SIMULATED_2;

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
        EXISTS_SIMULATED_2 = Classes.isExists(SIMULATED_2);
    }

    public static boolean isEmpty(final Collection<?> coll) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.collections4.CollectionUtils.isEmpty(coll);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.collections.CollectionUtils.isEmpty(coll);
        } else {
            return coll == null || coll.isEmpty();
        }
    }
}

