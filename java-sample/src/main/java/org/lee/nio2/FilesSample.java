package org.lee.nio2;

import java.io.IOException;
import java.nio.file.*;

public class FilesSample {

    public static void main(String[] args) {
        files_sample();
    }

    private static void files_sample() {


        Path src = Paths.get(".", "NIO2", "nio2-src.txt");
        Path desc = Paths.get(".", "NIO2", "nio2-desc.txt");

        try {
            Files.copy(src, desc, StandardCopyOption.REPLACE_EXISTING);

            Path path = Paths.get(".", "NIO2", "nio2-src.txt");

            testFiles(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFiles(Path path) {
        System.out.println("|取得内容|結果|");
        System.out.println("|---|---|");

        try {

            System.out.printf("|%s | %s |\r\n", "ディレクトリか", Files.isDirectory(path));
            System.out.printf("|%s | %s |\r\n", "隠しか", Files.isHidden(path));
            System.out.printf("|%s | %s |\r\n", "ディレクトリか", Files.isWritable(path));


            System.out.printf("|%s | %s |\r\n", "サイズは", Files.size(path));


            System.out.printf("|%s | " , "ファイル内容取得");
            Files.lines(path).forEach(line -> {
                System.out.printf("%s<br>" , line);
            });
            System.out.printf("|\r\n");

            System.out.printf("|%s | " , "ディレクトリ要素取得");
            Files.list(path.getParent()).forEach(p -> {
                System.out.printf("%s<br>" , p);
            });
            System.out.printf("|\r\n");

            System.out.printf("|%s | %s |\r\n", "ストレジ容量", Files.getFileStore(path).getTotalSpace() / 1024 / 1024 / 1024 + "GB");
            System.out.printf("|%s | %s |\r\n", "利用可能容量", Files.getFileStore(path).getUsableSpace() / 1024 / 1024 / 1024 + "GB");
            System.out.printf("|%s | %s |\r\n", "未割当て容量", Files.getFileStore(path).getUnallocatedSpace() / 1024 / 1024 / 1024 + "GB");

            System.out.printf("|%s | %s |\r\n", "存在するか", Files.exists(path));
            // Files.delete(path);

            System.out.printf("|%s | %s |\r\n", "削除する", "削除した");
            System.out.printf("|%s | %s |\r\n", "存在するか", Files.exists(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
