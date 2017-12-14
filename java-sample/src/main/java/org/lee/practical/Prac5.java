package org.lee.practical;

/**
 * polymorphism is better than instanceof
 */
public class Prac5 {


    public static void main(String[] args) {
        try {
            // m1();

            // m2();


            // System.out.println(m3());

//            long start = System.currentTimeMillis();
//            m4();
//            System.out.println(System.currentTimeMillis() - start);
//
//            start = System.currentTimeMillis();
//            m5();
//            System.out.println(System.currentTimeMillis() - start);

        } catch (Exception ex) {
            System.out.println("In main : " + ex.getMessage());
        }
    }

    private static void m4() {
        int sum = 0;
        for (int i = 0; i < 99900000; i++) {
            try {
                sum += i;
            } catch (Exception ex) {
            }
        }
    }

    private static void m5() {
        int sum = 0;
        try {
            for (int i = 0; i < 99900000; i++) {
                sum += i;
            }
        } catch (Exception ex) {
        }
    }

    private static void m1() throws Exception {
        try {
            System.out.println("m1 : try");
            throw new Exception("First EX.");
        } catch (Exception ex) {
            System.out.println("m1 : catch");
            throw new Exception("Second EX.");
        } finally {
            System.out.println("m1 : finally");
            throw new Exception("Third EX.");
        }
    }

    private static void m2() throws Exception {
        String msg = "";

        try {
            System.out.println("m2 : try");
            throw new Exception("First EX.");
        } catch (Exception ex) {
            System.out.println("m2 : catch");
            throw new Exception("Second EX." + ex.getMessage());
        } finally {
            System.out.println("m2 : finally");
            throw new Exception("Third EX.");
        }
    }

    private static int m3() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    private static class A {
        private final int 最大サイズ = 10;
        private int 現在のサイズ = 0;
        String[] values = new String[20];

        public void add(String val) throws Exception {
            現在のサイズ++;
            if (現在のサイズ > 最大サイズ) {
                throw new Exception("最大サイズ超えた");
            }

            values[現在のサイズ - 1] = val;
        }
    }


}
