package hL.easy.lang.model;

import hL.easy.lang.commons.Objects;

/**
 * @author @HL
 * @since 0.1.001
 */
public class ArrayNode<T> extends Kelue<Integer, T> implements INode<T> {

    private static final long serialVersionUID = -3840155816241815503L;

    public ArrayNode(Integer key, T value) {
        super(key, value);
    }

    @Override
    public T data() {
        return value();
    }

    @Override
    public ArrayNode<T> data(T data) {
        return value(data);
    }

    @Override
    public ArrayNode<T> key(Integer key) {
        return (ArrayNode<T>) super.key(key);
    }

    @Override
    public ArrayNode<T> value(T value) {
        return (ArrayNode<T>) super.value(value);
    }

    public int index() {
        return key();
    }

    public ArrayNode<T> index(int index) {
        return key(index);
    }

    @Override
    public ArrayNode<T> clone() {
        return new ArrayNode<>(key.intValue(), Objects.clone(this.value));
    }
}
