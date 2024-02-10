package org.prettyprint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.lang.model.type.PrimitiveType;
import java.util.List;
import java.util.Map;

public class PrettyPrinter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private PrettyPrinter() {
    }

    private static int check(int... pad) {
        if (pad.length == 1) {
            return pad[0];
        }
        return 0;
    }

    public static String pprint(Object o, int... pad) {
        if (o instanceof List<?>) {
            return iterWithList(((List<?>) o).toArray(), pad);
        } else if (o instanceof Map<?, ?>) {
            return iterWithMap((Map<?, ?>) o, pad);
        } else if (o instanceof Object[]) {
            return iterWithList((Object[]) o, pad);
        } else if (o instanceof CharSequence || o instanceof Number) {
            int p = check(pad);
            return " ".repeat(p) + o;
        } else {
            return iterWithObject(o, pad);
        }
    }

    private static String iterWithList(Object[] a, int... p) {

        int pad = check(p);

        StringBuilder sb = new StringBuilder();

        sb.append(" ".repeat(pad)).append("[");
        for (Object o : a) {
            sb.append("\n").append(pprint(o, pad + 1));
        }

        sb.append("\n").append(" ".repeat(pad)).append("]");

        return sb.toString();
    }

    private static String iterWithMap(Map<?, ?> m, int... p) {

        int pad = check(p);

        StringBuilder sb = new StringBuilder();

        int max = m.keySet().stream()
                .map(Object::toString)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        sb.append(" ".repeat(pad)).append("{");

        for (Object o : m.keySet()) {
            sb.append("\n").append(" ".repeat(pad + 1));
            sb.append(o).append(" ".repeat(max - o.toString().length())).append(" :\n");

            sb.append(pprint(m.get(o), pad + max+3));
        }
        sb.append("\n").append(" ".repeat(pad)).append("}");

        return sb.toString();
    }

    private static String iterWithObject(Object o, int... p) {


        Map<String, Object> json = objectMapper.convertValue(o, new TypeReference<>() {
        });

        return iterWithMap(json, p);

    }
}
