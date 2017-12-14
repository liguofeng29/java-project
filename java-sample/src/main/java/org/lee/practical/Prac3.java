package org.lee.practical;

/**
 * non-static non-private method will be overridden.
 *
 */
public class Prac3 {

    private static final class A {

    }

//    private static class A_sum extends A {
//
//    }


    private static class B {
        public void methodA() {}
        public final void methodB() {}
    }

    private static class B_sub extends B {
        public void methodA() {}

        // public final void methodB() {}
    }

    public static void main(String[] args) {


    }

}
