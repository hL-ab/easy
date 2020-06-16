package hL.easy.convert.converts;

import hL.easy.lang.commons.Classes;
import hL.easy.lang.exception.UnexpectedException;
import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.model.ArrayNode;
import hL.easy.lang.model.Kelue;
import hL.easy.lang.model.Pack;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class MapConvert<U, V> extends Pack<Map<U, V>> {

    private static final Logger logger = LoggerFactory.getLogger(MapConvert.class);

    public MapConvert(Map<? extends U, ? extends V> me) {
        super(filter(me));
    }

    private static <L, R> Map<L, R> filter(Map<? extends L, ? extends R> me) {
        if (me == null) {
            me = new HashMap<>();
        }
        return (Map<L, R>) me;
    }

    public MapConvert<V, V> toSingleMap(Map<V, V> to,
        U key, U value) {
        if (to == null) {
            try {
                to = Classes.instance(this.me.getClass());
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                logger.info(e.getMessage(), e);
                to = new HashMap<>();
            }
        }
        to.put(this.me.get(key), this.me.get(value));
        return new MapConvert<>(to);
    }

    public <W, X extends W> MapConvert<U, W> toMap(Map<U, X> to,
        Function1<Kelue<? super U, ? super V>, ? extends X> function) {
        if (to == null) {
            try {
                to = Classes.instance(this.me.getClass());
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                logger.info(e.getMessage(), e);
                to = new HashMap<>();
            }
        }
        if (function != null) {
            Map<U, X> finalTo = to;
            this.me.forEach((u, v) -> finalTo.put(u, function.invoke(new Kelue<>(u, v))));
        }
        return new MapConvert<>(to);
    }

}
