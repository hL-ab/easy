package hL.easy.lang.simulation.slf4j.Logger;

import hL.easy.lang.commons.Classes;

/**
 * @author @HL
 * @since 0.1.001
 */
public class LoggerFactory {

    public static final String SIMULATED_1 = "org.slf4j.LoggerFactory";
    public static final boolean EXISTS_SIMULATED_1;

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
    }

    public static Logger getLogger(Class<?> clazz) {
        if (EXISTS_SIMULATED_1) {
            return new Logger(org.slf4j.LoggerFactory.getLogger(clazz));
        }
        return new Logger(clazz);
    }
}
