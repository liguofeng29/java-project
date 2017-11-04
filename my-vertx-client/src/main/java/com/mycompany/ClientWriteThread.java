package com.mycompany;

import java.io.*;
import java.net.Socket;

public class ClientWriteThread extends Thread {

    private Socket socket;

    public ClientWriteThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();

        System.out.print("input > ");

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in));

            while (true) {
                String input = keyboard.readLine();

                out.write(input);
                out.flush();

                System.out.print("input > ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
