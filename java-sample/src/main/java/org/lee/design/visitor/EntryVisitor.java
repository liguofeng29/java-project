package org.lee.design.visitor;

import com.sun.org.apache.xpath.internal.SourceTree;

public class EntryVisitor extends Visitor {
    private String currentDir = "";


    @Override
    public void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory.getName());
        String saveDir = currentDir;

        currentDir = currentDir + "/" + directory.getName();

        for (Entry entry : directory.getDir()) {
           entry.accept(this);
        }

        currentDir = saveDir;
    }

    @Override
    public void visit(File file) {
        System.out.println(currentDir + "/" + file.getName());
    }
}
