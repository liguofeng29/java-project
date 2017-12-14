package org.lee.practical;

import java.awt.*;

/**
 * by value not by reference
 */
public class Prac1 {

    public static void main(String[] args) {

        Point p = new Point(0, 0);
        int i = 5;

        System.out.println("point is " + p);
        System.out.println("i is  " + i);

        modifyPoint(p, i);

        System.out.println("point is " + p);
        System.out.println("i is  " + i);

    }

    private static void modifyPoint(Point p, int i) {
        p.setLocation(5, 5);
        i = 10;
    }
}
