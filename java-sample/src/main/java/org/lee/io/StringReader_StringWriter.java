package org.lee.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReader_StringWriter {


    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        String src = "あいうえお" + System.lineSeparator()
            + "abcde" + System.lineSeparator()
            + "３行目";

        try (StringReader sr = new StringReader(src)) {

            char[] buff = new char[4];
            int hasRead = 0;

            while ((hasRead = sr.read(buff)) > 0) {
                System.out.print(new String(buff, 0, hasRead));
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (StringWriter sr = new StringWriter()) {
            sr.write("123");
            sr.write("あいう");

            System.out.println(sr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
