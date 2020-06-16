package hL.easy.convert.commons;

import hL.easy.convert.converts.CollectionConvert;
import hL.easy.convert.converts.MapConvert;
import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.function.Function.Function2;
import hL.easy.lang.model.ArrayNode;
import hL.easy.lang.model.Kelue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Convert {

    /**
     * 将指定序列以某种规则转换为另一序列。 原则上这种转换不应改变元素的数量。 如果指定的序列为空或转换规则为空，那么返回forw.<br/>
     * <b>事例：</b><br/>
     * <i>List&lt;Integer&gt; prev = new ArrayList&lt;Integer&gt;();</i><br/>
     * <i>List&lt;Integer&gt; forw = Convert.c2c(prev, (List&lt;Integer&gt;) null, (item, index) ->
     * item + index);</i><br/>
     * <i>[1,2,3,4,5]---->f(x,n)=x+n---->[1,3,5,7,9]</i><br/>
     *
     * @param from     要转换的序列
     * @param to       转换的结果。如果该参数为空，将构建一个新的序列
     * @param function 转换规则。该规则指明一个从原始序列的元素和元素索引到新序列的元素的一个映射
     * @param <U>
     * @param <V>
     * @param <W>
     * @return 转换之后的新的序列
     */
    public static <U, V, W extends Collection<V>> W c2C(Collection<? extends U> from,
        W to,
        Function1<ArrayNode<? super U>, ? extends V> function) {
        if (from == null) {
            return null;
        }
        if (to != null) {
            if (to instanceof List) {
                return (W) new CollectionConvert<U>(from).toList((List<V>) to, function).me();
            }
            //TODO
        }
        return (W) new CollectionConvert<U>(from).toCollection(to, function).me();
    }

    public static <U, V> Collection<V> c2C(Collection<? extends U> from,
        Function1<? super U, ? extends V> function) {
        if (from == null) {
            return null;
        }
        return from.stream().map(function).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * 从指定映射中选取两个值构成新的单元素映射。如果指定的映射为空，那么返回null.<br/>
     * <b>事例：</b><br/>
     * <i>Map&lt;Integer,Integer&gt; prev = new HashMap&lt;Integer,Integer&gt;();</i><br/>
     * <i>Map&lt;Integer,Integer&gt; forw = Convert.map2Map(prev, 1,2);</i><br/>
     * <i>{1:3,2:4,3:6}---->{3:4}</i><br/>
     *
     * @param from  要转换的映射
     * @param to    转换的结果。
     * @param key   新的单元素映射的键作为原始映射中的值所对应的键
     * @param value 新的单元素映射的值作为原始映射中的值所对应的键
     * @param <U>
     * @param <V>
     * @return 转换之后的新的映射
     */
    public static <U, V> Map<? super V, ? super V> m2SingleM(Map<U, V> from,
        Map<? super V, ? super V> to,
        U key, U value) {
        if (from == null) {
            return null;
        }
        return new MapConvert<>(from).toSingleMap((Map<V, V>) to, key, value).me();
    }

    /**
     * 将一个映射通过转换为一个另映射。这两个映射有相同的键，目标映射的值由指定规则生成。如果指定的映射为空，那么返回null.<br/>
     * <b>事例：</b><br/>
     * <i>Map&lt;Integer,Integer&gt; prev = new HashMap&lt;Integer,Integer&gt;();</i><br/>
     * <i>Map&lt;Integer,Integer&gt; forw = Convert.map2Map(prev, (key, value) -> key + value);</i>
     * <br/>
     * <i>{1:3,2:4,3:6}---->{1:4,2:6,3:9}</i><br/>
     *
     * @param from     要转换的映射
     * @param to       新的映射的值的映射规则
     * @param function 转换之后的新的映射
     * @param <U>
     * @param <V>
     * @param <W>
     * @param <X>
     * @return
     */
    public static <U, V, W, X extends W> Map<U, W> m2M(Map<U, V> from,
        Map<U, X> to, Function1<Kelue<? super U, ? super V>, ? extends X> function) {
        if (from == null) {
            return null;
        }
        MapConvert<U, W> m = new MapConvert<>(from).toMap(to, function);
        return m.me();
    }

    public static <U, V, W, X extends W> Map<U, W> m2M(Map<U, V> from,
        Function2<? super U, ? super V, ? extends X> function) {
        if (from == null) {
            return null;
        }
        Map<U, W> to = new HashMap<>();
        from.entrySet().forEach(e -> to.put(e.getKey(), function.invoke(e.getKey(), e.getValue())));
        return to;
    }

    /**
     * 将一个有序序列以某种规则转换为一个映射。 该转换规则指明一个从原始序列的元素和元素索引到新映射的键的一个映射，如果指定的序列为空，那么返回null。如果指定的转换规则为空，<br/>
     * 那么将返回一个键为null值为原始有序序列的单元素映射。 该键对应的值为该键通过指定转换规则的逆所得到的所有原始序列的元素构成的有序序列，并且其内元素是稳定的。<br/>
     * <b>事例：</b><br/>
     * <i>List&lt;Integer&gt; prev = new ArrayList&lt;Integer&gt;();</i><br/>
     * <i>Map&lt;Integer, List&lt;Integer&gt;&gt; forw = Convert.list2Map(prev, (item, index) ->
     * item - index);</i><br/>
     * <i>[1,2,3,3,6]---->f(x,n)=x-n---->{1:[1,2,3],0:[3],2:[6]}</i><br/>
     *
     * @param from     要转换的序列
     * @param to       转换规则
     * @param function 转换之后的新的映射
     * @param <U>
     * @param <V>
     * @param <W>
     * @return
     */
    public static <U, V, W extends V> Map<V, Collection<U>> cGroupToMap(
        Collection<? extends U> from,
        Map<W, Collection<U>> to, Function1<ArrayNode<? super U>, ? extends W> function) {
        if (from == null) {
            return null;
        }
        MapConvert<V, Collection<U>> m = new CollectionConvert<U>(from)
            .groupToMap(to, function);
        return m.me();
    }

    public static <U, V, W extends V> Map<V, List<U>> cGroupToMap(
        Collection<U> from, Function1<? super U, ? extends W> function) {
        if (from == null) {
            return null;
        }
        return from.stream()
            .collect(Collectors.groupingBy(function, LinkedHashMap::new, Collectors.toList()));
    }

}
