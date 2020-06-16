package hL.easy.convert.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import hL.easy.convert.model.JsonMaker;
import hL.easy.lang.commons.Classes;
import hL.easy.lang.commons.Objects;
import hL.easy.lang.exception.UnexpectedException;
import hL.easy.lang.function.Function.Function1;
import hL.easy.lang.simulation.commons.lang.math.NumberUtils;
import hL.easy.lang.simulation.slf4j.Logger.Logger;
import hL.easy.lang.simulation.slf4j.Logger.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author @HL
 * @since 0.1.001
 */
public final class Jsons {

    private static final Logger logger = LoggerFactory.getLogger(Jsons.class);

    private static final Pattern FAST_MATCHES1 =
        Pattern.compile("(\\w+)(?:(?:\\.\\w+)|(?:\\[\\w+\\]))*");
    private static final Pattern FAST_MATCHES2 =
        Pattern.compile("(?:(?:\\.(\\w+))|(?:\\[(\\w+)\\]))");

    public static final String SIMULATED_1 = "com.alibaba.fastjson.JSON";
    public static final String SIMULATED_2 = "com.fasterxml.jackson.databind.ObjectMapper";
    public static final boolean EXISTS_SIMULATED_1;
    public static final boolean EXISTS_SIMULATED_2;

    static {
        EXISTS_SIMULATED_1 = Classes.isExists(SIMULATED_1);
        EXISTS_SIMULATED_2 = Classes.isExists(SIMULATED_2);
    }

    public static Object fast(String key, Object value) {
        Matcher matcher1 = FAST_MATCHES1.matcher(key);
        if (!matcher1.matches()) {
            return null;
        }
        Matcher matcher2 = FAST_MATCHES2.matcher(key);
        Stack<String> stack = new Stack<>();
        stack.push(matcher1.group(1));
        while (matcher2.find()) {
            stack.push(matcher2.group(1) == null ? matcher2.group(2) : matcher2.group(1));
        }
        Object right = value;
        while (!stack.isEmpty()) {
            String left = stack.pop();
            if (NumberUtils.isDigits(left)) {
                int _left = Integer.parseInt(left);
                List<Object> list = new ArrayList<>(_left + 1);
                for (int i = 0; i <= _left; i++) {
                    list.add(null);
                }
                list.set(_left, right);
                right = list;
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put(left, right);
                right = map;
            }
        }
        return right;
    }

    public static Object fast(Map<String, Object> map) {
        return fast(map, null);
    }

    public static Object fast(Map<String, Object> map, Function1<Object, String> function) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        JsonMaker maker = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (maker == null) {
                if (function == null) {
                    if (EXISTS_SIMULATED_1) {
                        function =
                            com.alibaba.fastjson.JSON::toJSONString;
                    } else if (EXISTS_SIMULATED_2) {
                        function = o -> {
                            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
                            try {
                                return objectMapper.writeValueAsString(o);
                            } catch (JsonProcessingException e) {
                                logger.error(e.getMessage(), e);
                                throw new UnexpectedException(e);
                            }
                        };
                    } else {
                        logger.info("No default json parser found.");
                        function = o -> Objects.toString(o);
                    }
                }
                maker = JsonMaker.instance(entry.getKey(), entry.getValue(), function);
            } else {
                try {
                    maker.add(entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return maker.toJson();
    }

}
