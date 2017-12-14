package org.lee.practical;

/**
 * immutable
  */
public class Prac2 {

    private static class Obj {
        public int a = 0;
        public int b = 0;

        @Override
        public String toString() {
            return "a is " + a + System.lineSeparator()
                + "b is " + b;
        }
    }

    public static void main(String[] args) {
        final Obj obj = new Obj();
        System.out.println(obj);

        obj.a = 1;
        obj.b = 2;
        System.out.println(obj);
    }
}
