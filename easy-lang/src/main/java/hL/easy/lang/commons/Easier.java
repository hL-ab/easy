package hL.easy.lang.commons;

import hL.easy.lang.exception.UnexpectedException;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Easier {

    private static final Logger logger = LoggerFactory.getLogger(Easier.class);

    public static <T> T of(Class<T> clazz, Object... objects) {
        T result = null;
        int l = 0;
        if (objects != null) {
            l = objects.length;
        }
        try {
            if (Map.class.isAssignableFrom(clazz)) {
                Map<Object, Object> map = (Map<Object, Object>) clazz.newInstance();
                for (int i = 1; i < l; i += 2) {
                    map.put(objects[i - 1], objects[i]);
                }
                result = (T) map;
            } else if (List.class.isAssignableFrom(clazz)) {
                List<Object> list = (List<Object>) clazz.newInstance();
                list.addAll(Arrays.asList(objects));
                result = (T) list;
            } // TODO 其他类型
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Failed to initialize type {}. Because of: {}", clazz.getName(),
                e.getMessage());
            throw new UnexpectedException(e);
        }
        return result;
    }

    /**
     * 返回Iterable中指定位置的元素。<br/>
     *
     * @param o     指定Iterable
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static <T> T get(Iterable<T> o, int index, T def) {
        if (o == null || index < 0) {
            return def;
        }
        Iterator<T> iterator = o.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (i == index) {
                return t;
            }
        }
        return def;
    }

    /**
     * 返回Iterable中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定Iterable
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static <T> T get(Iterable<T> o, int index) {
        return get(o, index, null);
    }

    /**
     * 返回有序序列中指定位置的元素。<br/>
     *
     * @param o     指定有序序列
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static <T> T get(List<T> o, int index, T def) {
        if (o == null || o.size() <= index) {
            return def;
        }
        return o.get(index);
    }

    /**
     * 返回有序序列中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定有序序列
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static <T> T get(List<T> o, int index) {
        return get(o, index, null);
    }

    public static <T> T getEvenIfAbsent(List<T> o, int index, T def) {
        if (o == null) {
            return null;
        }
        T result = null;
        if ((result = get(o, index)) != null) {
            return result;
        }
        for (int i = o.size(); i <= index; i++) {
            o.add(null);
        }
        o.set(index, def);
        return def;
    }

    public static <T> int set(List<T> o, T prev, T forw, boolean isIdentity, boolean successFast) {
        int result = 0;
        if (o == null || o.size() == 0) {
            return result;
        }
        if (prev == null) {
            isIdentity = true;
        }
        int l = o.size();
        for (int i = 0; i < l; i++) {
            if ((isIdentity && o.get(i) == prev) || (!isIdentity && prev.equals(o.get(i)))) {
                o.set(i, forw);
                result++;
                if (successFast) {
                    break;
                }
            }
        }
        return result;
    }

    public static <T> int set(List<T> o, T prev, T forw) {
        return set(o, prev, forw, false, false);
    }

    /**
     * 返回映射中指定键的值。<br/>
     *
     * @param o     指定映射
     * @param index 指定键
     * @param def   默认值
     * @return 指定键的值
     */
    public static <U, V> V get(Map<U, V> o, U index, V def) {
        if (o == null || !o.containsKey(index)) {
            return def;
        }
        return o.get(index);
    }

    /**
     * 返回映射中指定键的值。若键不存在，则返回null。<br/>
     *
     * @param o     指定映射
     * @param index 指定键
     * @return 指定键的值
     */
    public static <U, V> V get(Map<U, V> o, U index) {
        return get(o, index, null);
    }

    public static <U, V> V getEvenIfAbsent(Map<U, V> o, U index, V def) {
        if (o == null) {
            return null;
        }
        o.putIfAbsent(index, def);
        return o.get(index);
    }

    public static <U, V> int set(Map<U, V> o, V prev, V forw, boolean isIdentity,
        boolean successFast) {
        int result = 0;
        if (o == null || o.size() == 0) {
            return result;
        }
        if (prev == null) {
            isIdentity = true;
        }
        for (Map.Entry<U, V> entry : o.entrySet()) {
            if ((isIdentity && entry.getValue() == prev)
                || (!isIdentity && prev.equals(entry.getValue()))) {
                entry.setValue(forw);
                result++;
                if (successFast) {
                    break;
                }
            }
        }
        return result;
    }

    public static <U, V> int set(Map<U, V> o, V prev, V forw) {
        return set(o, prev, forw, false, false);
    }


    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static <V> V get(V[] o, int index, V def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static <V> V get(V[] o, int index) {
        return get(o, index, null);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static byte get(byte[] o, int index, byte def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static byte get(byte[] o, int index) {
        return get(o, index, (byte) 0);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static short get(short[] o, int index, short def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static short get(short[] o, int index) {
        return get(o, index, (short) 0);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static int get(int[] o, int index, int def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static int get(int[] o, int index) {
        return get(o, index, 0);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static long get(long[] o, int index, long def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static long get(long[] o, int index) {
        return get(o, index, 0L);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static float get(float[] o, int index, float def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static float get(float[] o, int index) {
        return get(o, index, 0F);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static double get(double[] o, int index, double def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static double get(double[] o, int index) {
        return get(o, index, 0);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static boolean get(boolean[] o, int index, boolean def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static boolean get(boolean[] o, int index) {
        return get(o, index, true);
    }

    /**
     * 返回数组中指定位置的元素。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @param def   默认值
     * @return 指定位置的元素
     */
    public static char get(char[] o, int index, char def) {
        if (o == null || o.length <= index) {
            return def;
        }
        return o[index];
    }

    /**
     * 返回数组中指定位置的元素。若位置不存在，则返回null。<br/>
     *
     * @param o     指定数组
     * @param index 指定位置
     * @return 指定位置的元素
     */
    public static char get(char[] o, int index) {
        return get(o, index, ' ');
    }

    /**
     * 返回对象中指定字段的值。<br/>
     *
     * @param o     指定对象
     * @param index 指定字段
     * @param def   默认值
     * @return 指定字段的值
     */
    public static Object getField(Object o, String index, Object def) {
        try {
            return Classes.fieldValue(o, index);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger.info(e.getMessage(), e);
            return def;
        }
    }

    /**
     * 返回对象中指定字段的值。若字段不存在，则返回null。<br/>
     *
     * @param o     指定对象
     * @param index 指定字段
     * @return 指定字段的值
     */
    public static Object get(Object o, String index) {
        return get(o, index, null);
    }

    /**
     * 返回对象中指定字段的值。<br/>
     *
     * @param o     指定对象
     * @param index 指定字段
     * @param def   默认值
     * @return 指定字段的值
     */
    public static Object get(Object o, Object index, Object def) {
        return get(o, Strings.FROM.invoke(index), def);
    }

    /**
     * 返回对象中指定字段的值。若字段不存在，则返回null。<br/>
     *
     * @param o     指定对象
     * @param index 指定字段
     * @return 指定字段的值
     */
    public static Object get(Object o, Object index) {
        return get(o, index, null);
    }
}
