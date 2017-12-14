package org.lee.classloader;

import sun.misc.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

public class BootstrapSample {


    public static void main(String[] args) throws IOException {

        // load Bootstrap ClassLoader
        Arrays.stream(Launcher.getBootstrapClassPath().getURLs())
            .forEach(url -> System.out.println(url.toExternalForm()));

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        Enumeration<URL> eml = systemClassLoader.getResources("");

        while (eml.hasMoreElements()) {
            System.out.println(eml.nextElement());
        }

        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(systemClassLoader.getParent());
    }
}
