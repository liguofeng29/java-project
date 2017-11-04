package org.lee.java.aio;


//import io.vertx.core.Verticle;
//import io.vertx.core.http.HttpServerRequest;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

/**
 * tcpサーバ
 */
public class Server extends Verticle {

    // tcp接続
    public void start() {
        vertx.createNetServer()
            // 接続ハンドラー
            .connectHandler(socket -> {
                container.logger().info("socket connected");
                socket.dataHandler(new User(socket, this));
            }).listen(8081);
        container.logger().info("ChatVerticle started");
    }
}