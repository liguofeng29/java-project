package org.lee.innerclass;

public class Outer {
    String a = "outer-a";
    String b = "outer-b";
    String c ="outer-c";

    public class Inner {
        String a = "inner-a";
        String b = "inner-b";
        private void method() {
            String a = "method-a";

            System.out.println(a);
            System.out.println(b);
            System.out.println(c);

            System.out.println(this.a);
            System.out.println(Outer.this.a);
        }
    }

    public static void main(String[] args) {
        new Outer().new Inner().method();
    }
}
