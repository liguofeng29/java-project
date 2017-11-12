package org.lee.exception;

import org.apache.commons.lang.math.NumberUtils;

// 例外でプロセスを制御するな
public class Rule1 {

    public static void main(String[] args) {

        final int COUNT = 100000;

        String[] src = {"ab", "cd", "ef"};

        long start = 0L;

        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            show(src);
        }
        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            showWithEx(src);
        }
        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();
        for (int i = 0 ; i < COUNT; i++ ) {
            Object o = new ArrayIndexOutOfBoundsException();
        }
        System.out.println(System.currentTimeMillis() - start);
    }


    private static void showWithEx(String[] src) {
        try {
            int i = 0;

            while (true) {
                String s = src[i++];
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    private static void show(String[] src) {
        int i = 0;
        while (i < src.length) {
            String s = src[i++];
        }
    }
}
