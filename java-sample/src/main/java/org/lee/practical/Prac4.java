package org.lee.practical;

import java.util.stream.IntStream;

/**
 * Arrays and Vector
 */
public class Prac4 {


//    125
//        187
//        63
//        78
//        78
//        62


    public static void main(String[] args) {

        int count = 9999999;

        StackVars v1 =  new StackVars();

        long start = System.currentTimeMillis();
        v1.instanceAccess(count);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        v1.staticAccess(count);
        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();
        v1.stackAccess(count);
        System.out.println(System.currentTimeMillis() - start);



        StackVarsRefacotor v2 =  new StackVarsRefacotor();

        start = System.currentTimeMillis();
        v2.instanceAccess(count);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        v2.staticAccess(count);
        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();
        v2.stackAccess(count);
        System.out.println(System.currentTimeMillis() - start);



    }


    private static class StackVars {
        private int instVar;
        private static int staticVar;


        void stackAccess(int val) {
            int j = 0;
            for (int i = 0; i < val; i++) {
                j += 1;
            }
        }


        void instanceAccess(int val) {
            for (int i = 0; i < val; i++) {
                instVar += 1;
            }
        }

        void staticAccess(int val) {
            for (int i = 0; i < val; i++) {
                staticVar += 1;
            }
        }
    }

    private static class StackVarsRefacotor {
        private int instVar;
        private static int staticVar;

        void stackAccess(int val) {
            int j = 0;
            for (int i = 0; i < val; i++) {
                j += 1;
            }
        }


        void instanceAccess(int val) {
            int j = 0;
            for (int i = 0; i < val; i++) {
                j += 1;
            }

            instVar = j;
        }

        void staticAccess(int val) {
            int j = 0;

            for (int i = 0; i < val; i++) {
                j += 1;
            }

            staticVar = j;
        }
    }
}
