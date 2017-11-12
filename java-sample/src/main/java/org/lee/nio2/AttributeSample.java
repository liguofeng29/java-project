package org.lee.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class AttributeSample {

    public static void main(String[] args) throws IOException {
        testAttribute();
    }


    private static void testAttribute() throws IOException {
        Path path = Paths.get(".", "NIO2", "nio2-src.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);

        BasicFileAttributes attributes = view.readAttributes();

        System.out.println("前回アクセス : " + attributes.lastAccessTime());
        System.out.println("最新修正時刻 : " + attributes.lastModifiedTime());
    }
}
