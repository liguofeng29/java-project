package org.lee;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        demo1();

        demo2();

        demo3();

        demo4();

        demo5();

        demo6();

        demo7();
    }

    private static void demo1() {
        // 1.
        String a1 = "aaa";
        String a2 = "aaa";
        System.out.println(a1 == a2); // true

        // 2.
        String a3 = new String("aaa");
        String a4 = new String("aaa");
        System.out.println(a3 == a4); // false

        // 3.
        String a5 = "aaa";
        String a6 = new String("aaa");
        System.out.println(a5 == a6); // false
    }

    private static void demo2() {
        String a1 = "ab";
        String a2 = "a" + "b";
        System.out.println(a1 == a2); // true
    }

    private static void demo3() {
        String a1 = "ab";
        String a2 = "a" + new String("b");
        System.out.println(a1 == a2); // false
    }

    private static void demo4() {
        String a1 = "ab";
        String a2 = new String("ab");
        System.out.println(a1 == a2); // false

        a2 = a2.intern();
        System.out.println(a1 == a2); // true
    }

    private static void demo5() {
        String a1 = "ab";
        String a2 = "b";
        String a3 = "a" + a2;

        System.out.println(a1 == a3); // false
    }

    private static void demo6() {
        String a1 = "ab";
        final String a2 = "b";
        String a3 = "a" + a2;

        System.out.println(a1 == a3); // false
    }

    private static void demo7() {
        String s = null;
        for (int i = 0; i < 100; i++) {
            s += "a";
        }
    }

}

