package org.lee.design.flyweight;

import java.util.Arrays;

public class BigString {


    private BigChar[] bigChars;

    public BigString(String str) {

        bigChars = new BigChar[str.length()];

        BigCharFactory factory = BigCharFactory.getInstance();

        for (int i = 0; i < bigChars.length; i++) {
            bigChars[i] = factory.getBigChar(str.charAt(i));
        }
    }

    public void print() {

        Arrays.stream(bigChars)
            .forEach(BigChar::print);

    }


}
