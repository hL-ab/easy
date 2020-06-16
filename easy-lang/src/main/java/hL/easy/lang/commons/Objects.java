package hL.easy.lang.commons;

import hL.easy.lang.exception.UnexpectedException;
import hL.easy.lang.model.Jsonable;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.lang.reflect.InvocationTargetException;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Objects {

    private static final Logger logger = LoggerFactory.getLogger(Objects.class);

    public static <T> T clone(T t) {
        if (t != null && Cloneable.class.isAssignableFrom(t.getClass())) {
            try {
                return (T) Classes.invoke(t, "clone");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                logger.warn("{} is Cloneable but clone fail. Because of: {}", t.toString(),
                    e.getMessage());
                return t;
            } catch (Throwable e) {
                throw new UnexpectedException(e);
            }
        }
        //TODO
        logger.warn("{} is not Cloneable so we can not  clone it.", t.toString());
        return t;
    }

    public static String toString(Object o) {
        return Strings.FROM.invoke(o);
    }

    public static String toJson(Object o) {
        if (o == null) {
            return "{}";
        } else if (o instanceof CharSequence) {
            return "\"" + o + "\"";
        } else if (o instanceof Jsonable) {
            return ((Jsonable) o).toJson();
        } else {
            return toString(o);
        }
    }

    public static boolean equals(Object a, Object b) {
        return java.util.Objects.equals(a, b);
    }

    public static int hashCode(Object o) {
        return java.util.Objects.hashCode(o);
    }
}
