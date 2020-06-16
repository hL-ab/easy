package hL.easy.lang.function;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Function类为java提供了闭包。<br/> 该类的设想源自<i>Kotlin</i>。<br/>
 *
 * @author @HL
 * @since 0.1.001
 */
public interface Function {

    static <O> O invoke(Function0<O> function, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke();
    }

    static <U, O> O invoke(Function1<U, O> function, U u, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u);
    }

    static <U1, U2, O> O invoke(Function2<U1, U2, O> function, U1 u1, U2 u2, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2);
    }

    static <U1, U2, U3, O> O invoke(Function3<U1, U2, U3, O> function, U1 u1, U2 u2, U3 u3,
        O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3);
    }

    static <U1, U2, U3, U4, O> O invoke(Function4<U1, U2, U3, U4, O> function, U1 u1, U2 u2,
        U3 u3, U4 u4, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4);
    }

    static <U1, U2, U3, U4, U5, O> O invoke(Function5<U1, U2, U3, U4, U5, O> function, U1 u1,
        U2 u2, U3 u3, U4 u4, U5 u5, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4, u5);
    }

    static <U1, U2, U3, U4, U5, U6, O> O invoke(
        Function6<U1, U2, U3, U4, U5, U6, O> function, U1 u1, U2 u2, U3 u3, U4 u4, U5 u5, U6 u6,
        O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4, u5, u6);
    }

    static <U1, U2, U3, U4, U5, U6, U7, O> O invoke(
        Function7<U1, U2, U3, U4, U5, U6, U7, O> function, U1 u1, U2 u2, U3 u3, U4 u4, U5 u5,
        U6 u6, U7 u7, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4, u5, u6, u7);
    }

    static <U1, U2, U3, U4, U5, U6, U7, U8, O> O invoke(
        Function8<U1, U2, U3, U4, U5, U6, U7, U8, O> function, U1 u1, U2 u2, U3 u3, U4 u4,
        U5 u5, U6 u6, U7 u7, U8 u8, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4, u5, u6, u7, u8);
    }

    static <U1, U2, U3, U4, U5, U6, U7, U8, U9, O> O invoke(
        Function9<U1, U2, U3, U4, U5, U6, U7, U8, U9, O> function, U1 u1, U2 u2, U3 u3, U4 u4,
        U5 u5, U6 u6, U7 u7, U8 u8, U9 u9, O def) {
        if (function == null) {
            return def;
        }
        return function.invoke(u1, u2, u3, u4, u5, u6, u7, u8, u9);
    }


    /**
     * 没有入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function0<O> extends Function {

        O invoke();
    }

    /**
     * 有1个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function1<U, O> extends Function, java.util.function.Function<U, O>, Consumer<U> {

        O invoke(U u) throws JsonProcessingException;

        default O apply(U u) {
            return invoke(u);
        }

        default void accept(U u) {
            invoke(u);
        }
    }

    /**
     * 有2个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function2<U1, U2, O> extends Function, BiFunction<U1, U2, O>, BiConsumer<U1, U2> {

        O invoke(U1 u1, U2 u2);

        default O apply(U1 u1, U2 u2) {
            return invoke(u1, u2);
        }

        default void accept(U1 u1, U2 u2) {
            invoke(u1, u2);
        }
    }

    /**
     * 有3个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function3<U1, U2, U3, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3);
    }

    /**
     * 有4个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function4<U1, U2, U3, U4, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4);
    }

    /**
     * 有5个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function5<U1, U2, U3, U4, U5, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4, U5 u5);
    }

    /**
     * 有6个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function6<U1, U2, U3, U4, U5, U6, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4, U5 u5, U6 u6);
    }

    /**
     * 有7个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function7<U1, U2, U3, U4, U5, U6, U7, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4, U5 u5, U6 u6, U7 u7);
    }

    /**
     * 有8个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function8<U1, U2, U3, U4, U5, U6, U7, U8, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4, U5 u5, U6 u6, U7 u7, U8 u8);
    }

    /**
     * 有9个入参的闭包。<br/>
     *
     * @author @HL
     * @since 0.1.001
     */
    @FunctionalInterface
    interface Function9<U1, U2, U3, U4, U5, U6, U7, U8, U9, O> extends Function {

        O invoke(U1 u1, U2 u2, U3 u3, U4 u4, U5 u5, U6 u6, U7 u7, U8 u8, U9 u9);
    }
}
