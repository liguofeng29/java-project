package org.lee.design.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BigCharFactory {

    private Map<Character, BigChar> bigCharPool = new HashMap<>();

    private static BigCharFactory factory = new BigCharFactory();


    private BigCharFactory() {

    }

    public static BigCharFactory getInstance() {
        return factory;
    }


    public synchronized BigChar getBigChar(char name) {
        BigChar bigChar = bigCharPool.get(name);

        if (bigChar == null) {
            bigChar = new BigChar(name);
            bigCharPool.put(name,  bigChar);
        }
        return bigChar;
    }
}
