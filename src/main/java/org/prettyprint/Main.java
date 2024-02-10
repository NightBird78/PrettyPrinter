package org.prettyprint;

import com.sun.jdi.PrimitiveType;
import com.sun.jdi.PrimitiveValue;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("==== test 1 ====");

        String[] s = {"a", "b", "c"};

        String result = PrettyPrinter.pprint(s);

        System.out.println(result);

        System.out.println("==== test 2 ====");


        Map<Integer, String> map = Map.of(
                11, "a",
                2, "b");


        result = PrettyPrinter.pprint(map);

        System.out.println(result);

        System.out.println("==== test 3 ====");

        Map<Integer, List<String>> map2 = Map.of(
                11, List.of("a", "b"),
                2, List.of("c", "d"));


        result = PrettyPrinter.pprint(map2);

        System.out.println(result);

        System.out.println("==== test 4 ====");

        Map<Integer, List<Object>> map3 = Map.of(
                11, List.of(
                        new People("ford", 20, 180),
                        new People("ferrari", 30, 192)
                ),
                231, List.of(
                        new People("leonardo", 25, 175),
                        new People("ferrari", 27, 1187)
                )
        );


        result = PrettyPrinter.pprint(map3);

        System.out.println(result);



    }
}