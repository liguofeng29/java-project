package com.mycompany;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class User implements Handler<Buffer> {

    private final NetSocket socket;
    private final Set<String> userNames;
    private final EventBus eventBus;
    private Optional<String> userName;

    public User(NetSocket socket, Verticle verticle) {
        Vertx vertx = verticle.getVertx();
        this.socket = socket;

        this.userNames = vertx.sharedData().getSet("userNames");
        this.eventBus = vertx.eventBus();
        this.userName = Optional.empty();
    }

    @Override
    public void handle(Buffer buffer) {
        String receiveMessage = buffer.toString();
        System.out.println("receive from client : " + receiveMessage);
        if (!userName.isPresent()) {
            this.login(receiveMessage);
        } else {
            this.handleMessage(receiveMessage);
        }
    }

    private void login(String message) {
        if (message.startsWith("!login ")) {
            String userName = message.replace("!login ", "");

            this.userName = Optional.of(userName);
            this.userNames.add(this.userName.get());

            this.eventBus.registerHandler(this.userName.get(), (Message<String> msg) -> {
                sendToClient(msg.body());
            });

            sendToClient(this.userName.get());
        } else {
            sendToClient("please login with \"!login username\"");
        }
    }

    // 自分へ送信
    private void sendToClient(String message) {
        System.out.println("send to client : " + message);
        this.socket.write("receive > " + message);
    }

    // メッセージハンドリング
    private void handleMessage(String receiveMessage) {

        if (receiveMessage.equals("!all")) {
            this.showAllUserNames();

        } else if (receiveMessage.equals("!commands")) {
            this.showCommands();
        } else if (receiveMessage.startsWith("!send ")) {
            String sendMessage = receiveMessage.replace("!send ", "");
            this.sentToUser(sendMessage);
        } else if (receiveMessage.startsWith("!follow ")) {
            String followUserName = receiveMessage.replace("!follow ", "");
            this.followUser(followUserName);
        } else if (receiveMessage.startsWith("!publish")) {
            String messageBody = receiveMessage.replace("!publish ", "");
            this.publish(messageBody);
        } else {
            this.sendToClient(receiveMessage);
        }
    }

    private void showAllUserNames() {
        String userNames = this.userNames.stream()
            .collect(Collectors.joining(", "));

        this.sendToClient(userNames);
    }

    private void showCommands() {
        StringBuilder commmands = new StringBuilder();
        commmands.append("!login, ");
        commmands.append("!commands, ");
        commmands.append("!all, ");
        commmands.append("!publish, ");
        commmands.append("!follow, ");
        commmands.append("!send username:");

        this.sendToClient(commmands.toString());
    }

    //
    // 特定ユーザへ送信
    private void sentToUser(String message) {
        String userName = message.split(":")[0];
        String messageBody = message.split(":")[1];

        this.eventBus.send(userName, messageBody);
    }

    // 自分をフォローするユーザへ送信
    private void publish(String message) {
        String userName = this.userName.get();
        this.eventBus.publish(userName + ".followers", userName + '>' + message);
    }

    // 特定ユーザをフォロー
    private void followUser(String followUserName) {
        if (!this.userNames.contains(followUserName)) {
            this.sendToClient("存在しないユーザです");
        }

        this.eventBus.registerHandler(followUserName + ".followers", (Message<String> message) -> {
            this.sendToClient(message.body());
        });
    }
}