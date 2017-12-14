package org.lee.design.flyweight;

public class Main {

    // なにを共有させるかは慎重に選ぶ
    // intrinsic（本質的な） -> 共有させる情報
    // extrinsic（非本質的な）-> 共有させない情報
    //


    public static void main(String[] args) {
        BigCharFactory factory = BigCharFactory.getInstance();

        BigChar bc1 = factory.getBigChar('1');
        BigChar bc2 = factory.getBigChar('2');
        BigChar bc3 = factory.getBigChar('3');
        BigChar bc4 = factory.getBigChar('1');

        bc1.print();
        bc2.print();
        bc3.print();
        bc4.print();

        System.out.println(bc1 == bc4);

        new BigString("123123").print();
    }
}
