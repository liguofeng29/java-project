package org.lee.io;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileIOSample {


    public static void main(String[] args) throws IOException {

        // FileInputStream
        System.out.println("##FileInputStream##");
        demo1();

        System.out.println("##FileReader##");
        demo2();

        System.out.println("##FileOutputStream##");
        demo3();

        System.out.println("##FileWriter##");
        demo4();

        System.out.println("##FileWriter##");
        demo5();


        System.out.println("##PrintStream##");
        demo6();

    }


    private static void demo1() {

        try (FileInputStream fi = new FileInputStream("./FileIOSample/file-input.txt")) {
            // 4 byte のバッファー
            byte[] buff = new byte[4];

            // 読み取ったbyte数
            int hasRead = 0;

            // 最大buff分読み取る
            while ((hasRead = fi.read(buff)) > 0) {

                // 読み取ったbyteを文字列に変換して出力
                System.out.println(new String(buff, 0, hasRead));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static void demo2() throws IOException {
        try (FileReader fr = new FileReader("./FileIOSample/file-input.txt")) {
            // 4 char のバッファー
            char[] buff = new char[4];

            // 読み取った文字数
            int hasRead = 0;

            // 最大buff分読み取る
            while ((hasRead = fr.read(buff)) > 0) {

                // 読み取ったbyteを文字列に変換して出力
                System.out.println(new String(buff, 0, hasRead));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void demo3() {
        // byte操作
        try (
            FileInputStream fi = new FileInputStream("./FileIOSample/file-input.txt")) {
            FileOutputStream fo = new FileOutputStream("./FileIOSample/file-output-1.txt");

            // 4 byte のバッファー
            byte[] buff = new byte[4];

            // 読み取った単位数(byte数)
            int hasRead = 0;

            while ((hasRead = fi.read(buff)) > 0) {
                // 読み取った分書き込む
                fo.write(buff, 0, hasRead);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void demo4() {
        // char
        try (
            FileReader fr = new FileReader("./FileIOSample/file-input.txt");
            FileWriter fo = new FileWriter("./FileIOSample/file-output-2.txt")) {

            // 4 char のバッファー
            char[] buff = new char[4];

            // 読み取った単位数(byte数)
            int hasRead = 0;

            while ((hasRead = fr.read(buff)) > 0) {
                // 読み取った分書き込む
                fo.write(buff, 0, hasRead);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void demo5() {
        try (
            FileWriter fo = new FileWriter("./FileIOSample/file-output-3.txt")) {

            fo.write("行１");
            fo.write(System.lineSeparator());
            fo.write("行２");
            fo.write(System.lineSeparator());
            fo.write("行３");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void demo6() {

        try (
            FileOutputStream fo = new FileOutputStream("./FileIOSample/file-output-4.txt");
            PrintStream ps = new PrintStream(fo)){

            ps.println(1);
            ps.println("aa");
            ps.println("あいうえお");
            ps.println(new String("aあ"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        try (
            FileOutputStream fo = new FileOutputStream("./FileIOSample/file-output-5.txt");
            PrintWriter ps = new PrintWriter(fo)){

            ps.println(1);
            ps.println("aa");
            ps.println("あいうえお");
            ps.println(new String("aあ"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
