package org.lee.innerclass;

public class LocalInnerClass {


    public static void main(String[] args) {

        class Inner {
            protected int a = 1;
        }

        class InnerSub extends Inner {
            private int b = 2;
        }

        InnerSub sub = new InnerSub();
        System.out.println(sub.a + sub.b);
    }
}
