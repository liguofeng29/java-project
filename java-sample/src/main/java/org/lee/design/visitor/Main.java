package org.lee.design.visitor;

public class Main {

    public static void main(String[] args) {
        Directory rootDir = new Directory("root");
        Directory dir1 = new Directory("dir1");
        Directory dir2 = new Directory("dir2");
        Directory dir3 = new Directory("dir3");

        rootDir.add(dir1);
        rootDir.add(dir2);
        dir2.add(dir3);

        dir1.add(new File("file1-1", 10));
        dir1.add(new File("file1-2", 20));
        dir2.add(new File("file2-1", 30));
        dir3.add(new File("file3-1", 100));

        System.out.println("サイズ：" + rootDir.getSize());

        System.out.println("ディレクトリ構造：");
        rootDir.accept(new EntryVisitor());
    }
}
