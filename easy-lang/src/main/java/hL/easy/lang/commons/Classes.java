package hL.easy.lang.commons;

import hL.easy.annotation.edit.NotNull;
import hL.easy.lang.simulation.commons.lang.ArrayUtils;
import hL.easy.lang.simulation.commons.lang.StringUtils;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Classes {

    private static final Logger logger = LoggerFactory.getLogger(Classes.class);

    @NotNull
    public static final String SIMULATED_1 = "com.esotericsoftware.reflectasm.ConstructorAccess";
    @NotNull
    public static final boolean EXISTS_SIMULATED_1;
    @NotNull
    public static final String INVOKE_PATTERN_1 = "([a-zA-Z]+[0-9a-zA-Z_]*(\\.[a-zA-Z]+[0-9a-zA-Z_]*)+(\\$[a-zA-Z]+[0-9a-zA-Z_]*)*)\\.([a-zA-Z]+[0-9a-zA-Z_]*)\\((.*)\\)";

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
    }

    public static boolean isExists(String className) {
        try {
            getClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            logger.info(e.getMessage(), e);
            return false;
        }
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        return Thread.currentThread().getContextClassLoader().loadClass(className);
    }

    public static Object invoke(Class<?> clazz, String methodName, Object... args)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invoke(clazz, null, methodName, args);
    }

    public static Object invoke(Object o, String methodName, Object... args)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return o == null ? null : invoke(o.getClass(), o, methodName, args);
    }

    public static Object invoke(Class<?> clazz, Object object, String methodName, Object... args)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isBlank(methodName)) {
            return null;
        }
        if (clazz == null) {
            if (object != null) {
                return invoke(object.getClass(), object, methodName, args);
            } else {
                return null;
            }
        }
        if (EXISTS_SIMULATED_1) {
            try {
                MethodAccess access = MethodAccess.get(clazz);
                return access.invoke(object, methodName, args);
            } catch (Throwable e) {
                logger.warn(e.getMessage(), e);
                try {
                    return invokeByReflect(clazz, object, methodName, args);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e1) {
                    e1.addSuppressed(e);
                    throw e1;
                }
            }
        } else {
            return invokeByReflect(clazz, object, methodName, args);
        }
    }

    private static Object invokeByReflect(@NotNull Class<?> clazz, Object object,
        @NotNull String methodName,
        Object... args)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] clazzes = null;
        if (!ArrayUtils.isEmpty(args)) {
            clazzes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                clazzes[i] = args[i].getClass();
            }
        }
        Method method = clazz.getDeclaredMethod(methodName, clazzes);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method.invoke(object, args);
    }

    public static Object invoke(String string)
        throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (StringUtils.isBlank(string)) {
            return null;
        }
        Pattern pattern = Pattern.compile(INVOKE_PATTERN_1);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches() || matcher.groupCount() < 5) {
            return null;
        }
        String className = matcher.group(1);
        String methodName = matcher.group(4);
        if (StringUtils.isBlank(className) || StringUtils.isBlank(methodName)) {
            return null;
        }
        className = className.replaceAll("\\s", "");
        methodName = methodName.replaceAll("\\s", "");
        String _params = matcher.group(5);
        String[] params = null;
        if (_params != null) {
            List<String> list = new LinkedList<>();
            for (String s : _params.split(",")) {
                if (!StringUtils.isBlank(s)) {
                    list.add(s);
                }
            }
            params = list.toArray(new String[list.size()]);
        }
        @NotNull Class<?> clazz = getClass(className);
        try {
            return invoke(clazz, methodName, (Object[]) params);
        } catch (NoSuchMethodException e) {
            logger.info(
                "Parameter matching of method {} failed and fuzzy matching started. Because of: {}",
                methodName, e.getMessage());
            if (params == null) {
                return null;
            }
            try {
                return _invoke(clazz, methodName, params);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e1) {
                e1.addSuppressed(e);
                throw e1;
            }
        }
    }

    private static Object _invoke(@NotNull Class<?> clazz, @NotNull String methodName,
        @NotNull String[] params)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int l = params.length;
        int numberCount = l + 1;
        Object[] _params = null;
        A:
        for (Method method : clazz.getMethods()) {
            if (methodName.equals(method.getName())) {
                Class<?>[] clazzes = method.getParameterTypes();
                if (clazzes.length == l) {
                    Object[] __params = new Object[l];
                    int _numberCount = 0;
                    for (int i = 0; i < l; i++) {
                        Class<?> _clazz = clazzes[i];
                        if (String.class.equals(_clazz)) {
                            __params[i] = params[i];
                        } else {
                            Object o = Strings.to(params[i], _clazz);
                            if (o != null) {
                                _numberCount++;
                                __params[i] = o;
                            } else {
                                continue A;
                            }
                        }
                    }
                    if (_numberCount < numberCount) {
                        numberCount = _numberCount;
                        _params = __params;
                    }
                }
            }
        }
        if (_params == null) {
            return null;
        }
        return invoke(clazz, methodName, _params);
    }

    public static Object fieldValue(Object o, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        return o == null ? null : fieldValue(o.getClass(), o, fieldName);
    }

    public static Object fieldValue(Class<?> clazz, Object object, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        if (clazz == null) {
            if (object != null) {
                return fieldValue(object.getClass(), object, fieldName);
            } else {
                return null;
            }
        }
        if (EXISTS_SIMULATED_1) {
            try {
                FieldAccess access = FieldAccess.get(clazz);
                return access.get(object, fieldName);
            } catch (Throwable e) {
                logger.warn(e.getMessage(), e);
                try {
                    return fieldValueByReflect(clazz, object, fieldName);
                } catch (NoSuchFieldException | IllegalAccessException e1) {
                    e1.addSuppressed(e);
                    throw e1;
                }
            }
        } else {
            return fieldValueByReflect(clazz, object, fieldName);
        }
    }

    private static Object fieldValueByReflect(@NotNull Class<?> clazz, Object object,
        @NotNull String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(object);
    }


    public static <T> T instance(Class<T> clazz, Object... args)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (clazz == null || clazz.isInterface()) {
            return null;
        }
        if (EXISTS_SIMULATED_1) {
            try {
                ConstructorAccess<T> access = ConstructorAccess.get(clazz);
                return access.newInstance(args);
            } catch (Throwable e) {
                logger.warn(e.getMessage(), e);
                try {
                    return instanceByReflect(clazz, args);
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e1) {
                    e1.addSuppressed(e);
                    throw e1;
                }
            }
        } else {
            return instanceByReflect(clazz, args);
        }
    }

    private static <T> T instanceByReflect(@NotNull Class<T> clazz, Object[] args)
        throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (ArrayUtils.isEmpty(args)) {
            return clazz.newInstance();
        }
        Class<?>[] clazzes = new Class<?>[args.length];
        return (T) (clazz.getConstructor(clazzes).newInstance(args));
    }

}
