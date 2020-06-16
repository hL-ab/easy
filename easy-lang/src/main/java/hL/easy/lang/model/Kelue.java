package hL.easy.lang.model;

import hL.easy.lang.commons.Objects;
import java.io.Serializable;

/**
 * @author @HL
 * @since 0.1.001
 */
public class Kelue<U, V> implements Serializable, Cloneable, Jsonable {

    private static final long serialVersionUID = -8975119636992806119L;

    private transient static final String TO_JSON = "{\"key\":\"%s\",\"value\":\"%s\"}";

    protected U key;
    protected V value = null;

    public Kelue(U key, V value) {
        this.key = key;
        this.value = value;
    }

    public U key() {
        return this.key;
    }

    public Kelue<U, V> key(U key) {
        this.key = key;
        return this;
    }

    public V value() {
        return this.value;
    }

    public Kelue<U, V> value(V value) {
        this.value = value;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.key) << 16 & ~Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Kelue)) {
            return false;
        }
        return Objects.equals(this.key, ((Kelue<?, ?>) obj).key)
            && Objects.equals(this.value, ((Kelue<?, ?>) obj).value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{ " + this.key + " : " + this.value + " }";
    }

    @Override
    public Kelue<U, V> clone() {
        return new Kelue<>(Objects.clone(this.key), Objects.clone(this.value));
    }

    @Override
    public String toJson() {
        return String.format(TO_JSON, Objects.toJson(this.key), Objects.toJson(this.value));
    }
}
