package com.mycompany;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost"; //接続先
    private static final int PORT = 8081;           //80番ポートを使用

    public static void main(String[] args) {

        // ソケットの生成
        try (Socket socket = new Socket(HOST, PORT)) {

            socket.setSoTimeout(1000 * 60 * 60);

            new ClientReadThread(socket).start();

            new ClientWriteThread(socket).start();

            // 起動1時間有効
            Thread.sleep(1000 * 60 * 60);

        } catch (IOException | InterruptedException err) {
            err.printStackTrace();
        }
    }
}
