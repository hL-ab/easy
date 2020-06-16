package hL.easy.lang.commons;

import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.function.Function.Function2;
import hL.easy.lang.simulation.commons.lang.StringUtils;
import hL.easy.lang.simulation.commons.lang.math.NumberUtils;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Strings {

    private static final Logger logger = LoggerFactory.getLogger(Strings.class);

    public static final Function1<Object, String> FROM = u -> {
        String __;
        if (u instanceof String) {
            __ = (String) u;
        } else if (u instanceof AtomicInteger) {
            __ = String.valueOf(((AtomicInteger) u).get());
        } else if (u instanceof AtomicLong) {
            __ = String.valueOf(((AtomicLong) u).get());
        } else if (u instanceof BigDecimal) {
            __ = String.valueOf(((BigDecimal) u).doubleValue());
        } else if (u instanceof BigInteger) {
            __ = String.valueOf(((BigInteger) u).intValue());
        } else if (u instanceof Byte) {
            __ = String.valueOf((Byte) u);
        } else if (u instanceof Double) {
            __ = String.valueOf((Double) u);
        } else if (u instanceof Float) {
            __ = String.valueOf((Float) u);
        } else if (u instanceof Integer) {
            __ = String.valueOf((Integer) u);
        } else if (u instanceof Long) {
            __ = String.valueOf((Long) u);
        } else if (u instanceof Short) {
            __ = String.valueOf((Short) u);
        } else {
            __ = u.toString();
        }
        return __;
    };

    public static final Function2<String, Class<? extends Number>, Number> TO_NUMBER = (s, clazz) -> {
        if (clazz == null) {
            return null;
        }
        if (AtomicInteger.class.isAssignableFrom(clazz)) {
            return new AtomicInteger(NumberUtils.toInt(s));
        }
        if (AtomicLong.class.isAssignableFrom(clazz)) {
            return new AtomicLong(NumberUtils.toLong(s));
        }
        if (BigDecimal.class.isAssignableFrom(clazz)) {
            return NumberUtils.toScaledBigDecimal(s);
        }
        if (BigInteger.class.isAssignableFrom(clazz)) {
            return new BigInteger(s);
        }
        if (Byte.class.isAssignableFrom(clazz)) {
            return NumberUtils.toByte(s);
        }
        if (Double.class.isAssignableFrom(clazz)) {
            return NumberUtils.toDouble(s);
        }
        if (Float.class.isAssignableFrom(clazz)) {
            return NumberUtils.toFloat(s);
        }
        if (Integer.class.isAssignableFrom(clazz)) {
            return NumberUtils.toInt(s);
        }
        if (Long.class.isAssignableFrom(clazz)) {
            return NumberUtils.toLong(s);
        }
        if (Short.class.isAssignableFrom(clazz)) {
            return NumberUtils.toShort(s);
        }
        //java.util.concurrent.atomic.Striped64
        return null;
    };
    public static final Function2<String, Class<?>, Object> TO_PRIMITIVE = (s, clazz) -> {
        if (clazz == null || !clazz.isPrimitive()) {
            return null;
        }
        if (clazz == Boolean.TYPE) {
            return s != null && "true".equals(s.toLowerCase());
        }
        if (clazz == Character.TYPE) {
            return s == null ? ' ' : s.charAt(0);
        }
        if (clazz == Byte.TYPE) {
            return NumberUtils.toByte(s);
        }
        if (clazz == Double.TYPE) {
            return NumberUtils.toDouble(s);
        }
        if (clazz == Float.TYPE) {
            return NumberUtils.toFloat(s);
        }
        if (clazz == Integer.TYPE) {
            return NumberUtils.toInt(s);
        }
        if (clazz == Long.TYPE) {
            return NumberUtils.toLong(s);
        }
        if (clazz == Short.TYPE) {
            return NumberUtils.toShort(s);
        }
        //Void.TYPE
        return null;
    };

    public static int length(String s) {
        return s == null ? -1 : s.length();
    }

    public static <T> T to(String s, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        if (Number.class.isAssignableFrom(clazz)) {
            return (T) TO_NUMBER.invoke(s, (Class<? extends Number>) clazz);
        }
        if (clazz.isPrimitive()) {
            return (T) TO_PRIMITIVE.invoke(s, clazz);
        }
        if (Boolean.class.equals(clazz)) {
            return (T) new Boolean(s != null && "true".equals(s.toLowerCase()));
        }
        //TODO
        return null;
    }

    /**
     * 以指定值补齐字符串。该操作不改变原始对象。
     *
     * @param prev     要补齐的对象。该对象将首先通过指定转换规则转换为字符串。
     * @param n        补齐之后的位数
     * @param pad      用来补齐的字符串
     * @param ifNull   在要补齐的对象为空时代替该对象
     * @param noLonger 在要补齐的对象的长度大于指定的补齐之后的位数时，是否强制削减长度以满足指定长度。该削减优先取后几位
     * @param from     将要补齐的对象转换为字符串的规则。该规则为空时使用默认规则
     * @param isLeft   是否左补齐。否则右补齐
     * @return 补齐之后得到的字符串
     */
    public static String pad(Object prev, int n, String pad, String ifNull, boolean noLonger,
        Function1<Object, String> from, boolean isLeft) {
        if (ifNull == null) {
            ifNull = "";
        }
        if (prev == null) {
            prev = ifNull;
        }
        if (from == null) {
            from = Strings.FROM;
        }
        String __ = from.invoke(prev);
        if (n == 0) {
            return "";
        } else if (n < 1) {
            return __;
        } else if (noLonger && __.length() > n) {
            return __.substring(__.length() - n);
        } else if (isLeft) {
            return StringUtils.leftPad(__, n, pad);
        } else {
            return StringUtils.rightPad(__, n, pad);
        }
    }

    /**
     * 左补齐，参见 {@link #pad(Object, int, String, String, boolean, Function1, boolean)}。
     */
    public static String lpad(Object prev, int n, String pad, String ifNull, boolean noLonger,
        Function1<Object, String> from) {
        return pad(prev, n, pad, ifNull, noLonger, from, true);
    }

    /**
     * 右补齐，参见 {@link #pad(Object, int, String, String, boolean, Function1, boolean)}。
     */
    public static String rpad(Object prev, int n, String pad, String ifNull, boolean noLonger,
        Function1<Object, String> from) {
        return pad(prev, n, pad, ifNull, noLonger, from, false);
    }

    /**
     * 使用空字符串和默认转换规则进行强制削减的左补齐，参见 {@link #pad(Object, int, String, String, boolean, Function1,
     * boolean)}。
     */
    public static String lpad(Object prev, int n, String pad) {
        return pad(prev, n, pad, "", true, Strings.FROM, true);
    }

    /**
     * 使用空字符串和默认转换规则进行强制削减的右补齐，参见 {@link #pad(Object, int, String, String, boolean, Function1,
     * boolean)}。
     */
    public static String rpad(Object prev, int n, String pad) {
        return pad(prev, n, pad, "", true, Strings.FROM, false);
    }
}
