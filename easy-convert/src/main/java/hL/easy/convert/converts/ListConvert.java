package hL.easy.convert.converts;

import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.model.ArrayNode;
import hL.easy.lang.model.Pack;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ListConvert<T> extends Pack<List<T>> {

    private static final Logger logger = LoggerFactory.getLogger(ListConvert.class);

    public ListConvert(List<? extends T> me) {
        super(filter(me));
    }

    private static <U> List<U> filter(List<? extends U> me) {
        if (me == null) {
            me = new ArrayList<>(1);
        }
        return (List<U>) me;
    }

}
