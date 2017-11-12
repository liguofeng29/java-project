package org.lee.nio2;

import java.io.IOException;
import java.nio.file.*;

public class WatchServiceSample {


    public static void main(String[] args) {
        testWatchService();
    }

    private static void testWatchService() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Paths.get(".").register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE, // CREATE監視
                StandardWatchEventKinds.ENTRY_DELETE, // DELETE監視
                StandardWatchEventKinds.ENTRY_MODIFY  // MODIFY監視
            );

            System.out.println("-----watch開始-----");
            while (true) {
                // 待ち続ける
                WatchKey key = watchService.take();

                key.pollEvents().stream()
                    .forEach(
                        watchEvent -> {
                            System.out.println("発生種類 : " + watchEvent.kind().name());
                            System.out.println("対象名　 : " + watchEvent.context());

                        });

                // 次の監視
                if (!key.reset()) {
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
