package com.mycompany;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

public class Server extends Verticle {
    public void start() {
        vertx.createNetServer()
            // socket接続ハンドラー
            .connectHandler(socket -> {
                container.logger().info("socket connected!");
                // データ受信ハンドラー
                socket.dataHandler(new User(socket, this));
            }).listen(8081);
        container.logger().info("TCP Server started.");
    }
}








