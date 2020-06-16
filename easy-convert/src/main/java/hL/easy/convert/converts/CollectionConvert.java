package hL.easy.convert.converts;

import hL.easy.lang.commons.Classes;
import hL.easy.lang.exception.UnexpectedException;
import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.model.ArrayNode;
import hL.easy.lang.model.Pack;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CollectionConvert<T> extends Pack<Collection<T>> {

    private static final Logger logger = LoggerFactory.getLogger(CollectionConvert.class);

    public CollectionConvert(Collection<? extends T> me) {
        super(filter(me));
    }

    private static <U> Collection<U> filter(Collection<? extends U> me) {
        if (me == null) {
            me = new ArrayList<>(1);
        }
        return (Collection<U>) me;
    }

    public <V, W extends V> CollectionConvert<V> toCollection(Collection<W> to,
        Function1<ArrayNode<? super T>, ? extends W> function) {
        int l = this.me.size();
        if (to == null) {
            try {
                to = Classes.instance(this.me.getClass());
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                logger.error(e.getMessage(), e);
                throw new UnexpectedException("Unable to build target collection!");
            }
        }
        int i = 0;
        for (T t : this.me) {
            to.add(function == null ? null : function.invoke(new ArrayNode<>(i, t)));
            i++;
        }
        return new CollectionConvert<>(to);
    }

    public <V, W extends V> ListConvert<V> toList(List<W> to,
        Function1<ArrayNode<? super T>, ? extends W> function) {
        int l = this.me.size();
        if (to == null) {
            try {
                if (this.me instanceof List) {
                    to = (List<W>) Classes.instance(this.me.getClass());
                }
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                logger.info(e.getMessage(), e);
            }
        }
        if (to == null) {
            to = new ArrayList<>(l + 1);
        }
        int i = 0;
        for (T t : this.me) {
            to.add(function == null ? null : function.invoke(new ArrayNode<>(i, t)));
            i++;
        }
        return new ListConvert<>(to);
    }

    public <V, W extends V> MapConvert<V, Collection<T>> groupToMap(
        Map<W, Collection<T>> to, Function1<ArrayNode<? super T>, ? extends W> function) {
        int l = this.me.size();
        if (to == null) {
            to = new LinkedHashMap<>();
        }
        int i = 0;
        for (T t : this.me) {
            W key = function == null ? null : function.invoke(new ArrayNode<>(i, t));
            to.computeIfAbsent(key, k -> new ArrayList<>(4)).add(t);
            i++;
        }
        return new MapConvert<>(to);
    }
}
