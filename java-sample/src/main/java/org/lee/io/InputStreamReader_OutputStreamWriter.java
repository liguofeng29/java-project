package org.lee.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReader_OutputStreamWriter {


    public static void main(String[] args) {
        demo1();
    }


    private static void demo1() {

        try (InputStreamReader reader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;

            System.out.print("input:");
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(0);
                }

                System.out.println("output:" + line);
                System.out.print("input:");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
