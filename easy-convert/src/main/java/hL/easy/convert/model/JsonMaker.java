package hL.easy.convert.model;

import hL.easy.convert.commons.Convert;
import hL.easy.convert.commons.Jsons;
import hL.easy.lang.commons.Easier;
import hL.easy.lang.commons.Objects;
import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.simulation.commons.lang.math.NumberUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author @HL
 * @since 0.1.001
 */
public abstract class JsonMaker {

    public static final Pattern ALL_MATCH =
        Pattern.compile("(\\w+)(?:(?:\\.\\w+)|(?:\\[\\w+\\]))*");
    public static final Pattern FIND_MATCH = Pattern.compile("(?:(?:\\.(\\w+))|(?:\\[(\\w+)\\]))");

    private R root;

    public JsonMaker(Object o) {
        root = new R(o);
    }

    public JsonMaker(String key, Object value) {
        this(Jsons.fast(key, value));
    }

    public static JsonMaker instance(Object o, Function1<Object, String> function) {
        return new JsonMaker(o) {
            @Override
            public String toJson(Object jsonObject) {
                return function == null ? Objects.toString(jsonObject)
                    : function.invoke(jsonObject);
            }
        };
    }

    public static JsonMaker instance(String key, Object value, Function1<Object, String> function) {
        return new JsonMaker(key, value) {
            @Override
            public String toJson(Object jsonObject) {
                return function == null ? Objects.toString(jsonObject)
                    : function.invoke(jsonObject);
            }
        };
    }

    public JsonMaker add(String key, Object value) throws Exception {
        C<?> o = root.me();
        Matcher matcher1 = ALL_MATCH.matcher(key);
        if (!matcher1.matches()) {
            return this;
        }
        String s1 = matcher1.group(1);
        String s2 = s1;
        Matcher matcher2 = FIND_MATCH.matcher(key);
        while (matcher2.find()) {
            s2 = matcher2.group(1) == null ? matcher2.group(2) : matcher2.group(1);
            o = o.add(s1, s2, false);
            if (o == null) {
                root = null;
                throw Exception.SENTENCE_CONFLICT;
            }
            s1 = s2;
        }
        if (o.add(s1, value, true) == null) {
            root = null;
            throw Exception.SENTENCE_CONFLICT;
        }
        return this;
    }

    public Object toJson() {
        return root == null ? null : root.toJson();
    }

    public abstract String toJson(Object jsonObject);

    @Override
    public String toString() {
        return toJson(toJson());
    }

    static abstract class C<T> {

        protected C<?> up;

        protected T me = null;

        public C(C<?> up) {
            this.up = up;
        }

        public T me() {
            return me;
        }

        public C<T> me(T me) {
            this.me = me;
            return this;
        }

        public abstract C<?> add(String key, Object value, boolean isLast);

        public abstract Object toJson();

        @SuppressWarnings("unchecked")
        public static C<?> of(Object o, C<?> up) {
            if (o instanceof List) {
                return new L((List<Object>) o, up);
            } else if (o instanceof Map) {
                return new M((Map<String, Object>) o, up);
            } else {
                return new O(o, up);
            }
        }

        @Override
        public int hashCode() {
            return me() == null ? 0 : me().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof C<?> && obj != null
                ? me() == null ? ((C<?>) obj).me() == null : me().equals(((C<?>) obj).me())
                : false;
        }

        @Override
        public String toString() {
            return me() == null ? "null" : me().toString();
        }

    }

    static class R extends C<C<?>> {

        public R(Object o) {
            super(null);
            me(C.of(o, this));
        }

        @Override
        public R me(C<?> me) {
            super.me(me);
            return this;
        }

        @Override
        public Object toJson() {
            return me().toJson();
        }

        @Override
        public C<?> add(String key, Object value, boolean isLast) {
            return null;
        }

    }

    static class O extends C<Object> {

        public O(Object o, C<?> up) {
            super(up);
            this.me(o);
        }

        @Override
        public O me(Object me) {
            super.me(me);
            return this;
        }

        @Override
        public Object toJson() {
            return me();
        }

        @Override
        public C<?> add(String key, Object value, boolean isLast) {
            return null;
        }
    }

    static class L extends C<List<C<?>>> {

        public L(List<Object> list, C<?> up) {
            super(up);
            this.me(Convert.c2C(list, new ArrayList<C<?>>((list == null ? 0 : list.size()) + 1),
                o -> C.of(o.data(), this)));
        }

        @Override
        public L me(List<C<?>> me) {
            super.me(me);
            return this;
        }

        @Override
        public Object toJson() {
            return Convert.c2C(me(),
                o -> o == null ? null : o.toJson());
        }

        @Override
        public C<?> add(String key, Object value, boolean isLast) {
            if (NumberUtils.isDigits(key)) {
                int index = Integer.parseInt(key);
                C<?> _new;
                if (isLast) {
                    _new = new O(value, this);
                    Easier.getEvenIfAbsent(me(), index, _new);
                    me().set(index, _new);
                    return _new;
                } else if ((_new = Easier.get(me(), index)) != null) {
                    return _new;
                } else if (NumberUtils.isDigits((String) value)) {
                    _new = new L(null, this);
                } else {
                    _new = new M(null, this);
                }
                return Easier.getEvenIfAbsent(me(), index, _new);
            } else {
                M _new = new M(null, up);
                _new.me(Convert.m2M(Convert.cGroupToMap(me(), null, e -> String.valueOf(e.index())),
                    (k, v) -> Easier.get(v, 0)));
                if (up instanceof R) {
                    ((R) up).me(_new);
                } else if (up instanceof L) {
                    Easier.set(((L) up).me(), this, _new, true, true);
                } else if (up instanceof M) {
                    Easier.set(((M) up).me(), this, _new, true, true);
                }
                me(null);
                up = null;
                return _new.add(key, value, isLast);
            }
        }
    }

    static class M extends C<Map<String, C<?>>> {

        public M(Map<String, Object> map, C<?> up) {
            super(up);
            this.me(Convert.m2M(map, (k, v) -> C.of(v, this)));
        }

        @Override
        public M me(Map<String, C<?>> me) {
            super.me(me);
            return this;
        }

        @Override
        public Object toJson() {
            return Convert
                .m2M(me(), (k, v) -> v == null ? null : v.toJson());
        }

        @Override
        public C<?> add(String key, Object value, boolean isLast) {
            return Easier.getEvenIfAbsent(me(), key, isLast ? new O(value, this)
                : NumberUtils.isDigits((String) value) ? new L(null, this) : new M(null, this));
        }
    }

    //TODO
    public static class Exception extends java.lang.Exception {

        /**
         *
         */
        private static final long serialVersionUID = 6465398726954163868L;

        public final static Exception SENTENCE_CONFLICT =
            new Exception("json编译语句与已有结构冲突，该冲突导致已破坏已有结构，请勿继续使用。");

        public Exception() {
            super();
        }

        public Exception(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public Exception(String message, Throwable cause) {
            super(message, cause);
        }

        public Exception(String message) {
            super(message);
        }

        public Exception(Throwable cause) {
            super(cause);
        }

    }

}
