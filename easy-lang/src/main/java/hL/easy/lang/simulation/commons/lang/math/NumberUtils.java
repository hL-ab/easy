package hL.easy.lang.simulation.commons.lang.math;

import hL.easy.lang.commons.Classes;
import hL.easy.lang.simulation.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author @HL
 * @since 0.1.001
 */
public class NumberUtils {

    public static final String SIMULATED_1 = "org.apache.commons.lang3.math.NumberUtils";
    public static final String SIMULATED_2 = "org.apache.commons.lang.math.NumberUtils";
    public static final boolean EXISTS_SIMULATED_1;
    public static final boolean EXISTS_SIMULATED_2;

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
        EXISTS_SIMULATED_2 = Classes.isExists(SIMULATED_2);
    }

    public static int toInt(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toInt(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toInt(s);
        } else {
            return s == null ? 0 : Integer.parseInt(s);
        }
    }

    public static long toLong(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toLong(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toLong(s);
        } else {
            return s == null ? 0L : Long.parseLong(s);
        }
    }

    public static byte toByte(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toByte(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toByte(s);
        } else {
            return s == null ? (byte) 0 : Byte.parseByte(s);
        }
    }

    public static double toDouble(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toDouble(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toDouble(s);
        } else {
            return s == null ? 0.0D : Double.parseDouble(s);
        }
    }

    public static float toFloat(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toFloat(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toFloat(s);
        } else {
            return s == null ? 0.0f : Float.parseFloat(s);
        }
    }

    public static short toShort(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toShort(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.toShort(s);
        } else {
            return s == null ? (short) 0 : Short.parseShort(s);
        }
    }

    public static BigDecimal toScaledBigDecimal(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.toScaledBigDecimal(s);
        } else {
            BigDecimal value;
            if (!StringUtils.isBlank(s)) {
                value = new BigDecimal(s);
                value.setScale(2, RoundingMode.HALF_EVEN);
                return value;
            } else {
                throw new NumberFormatException("A blank string is not a valid number");
            }
        }
    }

    public static boolean isDigits(String s) {
        if (EXISTS_SIMULATED_1) {
            return org.apache.commons.lang3.math.NumberUtils.isDigits(s);
        } else if (EXISTS_SIMULATED_2) {
            return org.apache.commons.lang.math.NumberUtils.isDigits(s);
        } else {
            return StringUtils.isNumeric(s);
        }
    }


}
