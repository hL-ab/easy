package hL.easy.lang.model;

/**
 * @author @HL
 * @since 0.1.001
 */
public interface INode<T> {

    T data();

    INode<T> data(T data);
}
