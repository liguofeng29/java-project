package org.lee.exception;

// 膨大なtryは使うな
public class Rule2 {

    public static void main(String[] args) {
        try {
            String src;
            src = "123";
            int val1 = Integer.parseInt(src);

            src = "abc";
            int val2 = Integer.parseInt(src);

            src = null;
            src.getBytes();

        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex ) {
            System.out.println(ex.getMessage());
        }
    }
}
