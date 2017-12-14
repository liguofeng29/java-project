package org.lee.design.flyweight;

import java.io.*;

public class BigChar {

    private char name;
    private String value;

    public BigChar(char name) {
        this.name = name;

        try (BufferedReader br = new BufferedReader(
                                    new InputStreamReader(BigChar.class.getClassLoader().getResourceAsStream(name + ".txt"))
                                )) {

            String line;


            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            this.value = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println(value);
    }
}
