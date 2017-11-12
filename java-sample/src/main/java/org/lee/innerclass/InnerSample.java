package org.lee.innerclass;

public class InnerSample {

    class In {
        void method() {
            // Outer.Inner inner = new Outer().new Inner();
//
//            System.out.println(inner.publicC);
//            System.out.println(inner.protectedB);
        }
    }
    public static void main(String[] args) {
        new InnerSample().new In().method();
//        Outer.Inner inner = new Outer().new Inner();
//        System.out.println(inner.publicC);
//        System.out.println(inner.protectedB);

        // NG
        // System.out.println(inner.privateA);
    }
}


