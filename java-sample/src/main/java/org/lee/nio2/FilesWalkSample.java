package org.lee.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class FilesWalkSample {

    public static void main(String[] args) {
        files_sample();
    }

    private static void files_sample() {
        try {

            Files.walk(Paths.get(".", "NIO2"), FileVisitOption.FOLLOW_LINKS)
                // directoryとfile入れ替え
                .sorted(Comparator.reverseOrder())
                // 削除対象確認
                .peek(System.out::println)
                // 削除
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
