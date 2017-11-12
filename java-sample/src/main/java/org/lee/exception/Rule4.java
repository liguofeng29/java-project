package org.lee.exception;

import java.io.*;

// 例外を無視するな
public class Rule4 {

    public static void main(String[] args) {
        try {
            File f = new File("存在しない");
            int b = new FileInputStream(f).read();

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
