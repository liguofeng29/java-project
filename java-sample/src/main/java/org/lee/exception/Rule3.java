package org.lee.exception;

import java.io.File;
import java.io.FileInputStream;

// Catch Allは使うな
public class Rule3 {

    public static void main(String[] args) {
        try {
            // Checked Exception
            File f = new File("存在しない");
            int b = new FileInputStream(f).read();

            // Runtime Exception
            Object o = null;
            o.getClass();


        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
