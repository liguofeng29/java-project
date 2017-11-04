package com.mycompany;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class ClientReadThread extends Thread {

    private Socket socket;

    public ClientReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            char[] buf = new char[1024];

            while (true) {
                if (in.read(buf) != -1) {
                    System.out.print(String.valueOf(buf).trim());
                    System.out.println();
                    System.out.print("input > ");

                    // なぜか、バッファがクリアされないので
                    for (int i = 0; i < buf.length; i++) {
                        buf[i] = ' ';
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
