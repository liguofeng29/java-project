package org.lee.java.aio;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class User implements Handler<Buffer> {

    private static final Pattern newline = Pattern.compile("\\n");

    private final NetSocket socket;

    private final Set<String> names;

    private final EventBus eventBus;

    private Optional<String> name;


    // 新しいユーザ
    public User(NetSocket socket, Verticle verticle) {
        Vertx vertx = verticle.getVertx();
        this.socket = socket;
        names = vertx.sharedData().getSet("names");
        eventBus = vertx.eventBus();
        name = Optional.empty();
    }

    @Override
    public void handle(Buffer buffer) {
        newline.splitAsStream(buffer.toString())
            .forEach(line -> {
                if (!name.isPresent())
                    setName(line);
                else
                    handleMessage(line);
            });
    }

    private void handleMessage(String line) {

        eventBus.registerHandler(name.get(), (Message<String> msg) -> {

            System.out.println("name is " + name);
           // sendClient(msg.body());
        });

    }


    public void setName(String name) {
        this.names.add(name);
    }


    // send to all
    private void broadcastMessage(String message) {
        String name = this.name.get();
        eventBus.publish(name + ".followers", name + '>' + message);
    }

    // receive from all
//    private void followUser(String user) {
//        eventBus.registerHandler(user + ".followers", (Message<String> message) -> {
//            sendClient(message.body());
//        });
//    }
}