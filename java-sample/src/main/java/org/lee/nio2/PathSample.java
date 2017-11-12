package org.lee.nio2;

import com.sun.javafx.binding.StringFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathSample {

    public static void main(String[] args) {
        path_test();
    }

    private static void path_test() {
        // 相対パス
        Path relativePath = Paths.get(".", "NIO2", "sub", "nio2-file.txt");
        System.out.println("---- 相対パス ----");
        showPath(relativePath);

        // 絶対パス
        System.out.println("---- 絶対パス ----");
        showPath(relativePath.toAbsolutePath());
    }

    private static void showPath(Path path) {
        System.out.println("|取得内容|結果|");
        System.out.println("|---|---|");
        System.out.printf("|%s | %s |\r\n", "要素数", path.getNameCount());
        System.out.printf("|%s | %s |\r\n", "指定要素名", path.getName(0));
        System.out.printf("|%s | %s |\r\n", "ファイル名", path.getFileName());
        System.out.printf("|%s | %s |\r\n", "ファイルシステム名", path.getFileSystem());
        System.out.printf("|%s | %s |\r\n", "親", path.getParent());
        System.out.printf("|%s | %s |\r\n", "ルート", path.getRoot());
        System.out.printf("|%s | %s |\r\n", "絶対パスか", path.isAbsolute());
    }
}
