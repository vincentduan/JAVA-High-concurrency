package com.vincent.example.immutable;


import com.vincent.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(2, 3);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map  = new HashMap<>();
        map.put(1, 3);
        map.put(3,4);
        log.info("{}", map.get(3));
    }



}
