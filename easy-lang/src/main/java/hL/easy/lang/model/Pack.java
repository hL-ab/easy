package hL.easy.lang.model;

import hL.easy.lang.commons.Objects;
import java.io.Serializable;

/**
 * @author @HL
 * @since 0.1.001
 */
public class Pack<T> implements Serializable, Cloneable, Jsonable, Comparable<Pack<T>> {

    private static final long serialVersionUID = -1953929139212363059L;

    protected T me;

    public Pack(T me) {
        this.me = me;
    }

    public static <T> Pack<T> of(T it) {
        return new Pack<>(it);
    }

    public T me() {
        return this.me;
    }

    public Pack<T> me(T me) {
        this.me = me;
        return this;
    }

    public T get() {
        return me();
    }

    public Pack<T> put(T me) {
        return me(me);
    }

    @Override
    public int compareTo(Pack<T> o) {
        if (this.me == null) {
            return o.me == null ? 0 : -1;
        }
        if (o.me == null) {
            return 1;
        }
        if (this.me instanceof Comparable) {
            return ((Comparable<T>) this.me).compareTo(o.me);
        } else {
            return this.me.hashCode() - o.me.hashCode();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.me) + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Pack)) {
            return false;
        }
        return Objects.equals(this.me, ((Pack<?>) obj).me);
    }

    @Override
    public Pack<T> clone() {
        return new Pack<T>(Objects.clone(this.me));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[ " + this.me + " ]";
    }

    @Override
    public String toJson() {
        return Objects.toJson(me);
    }
}
