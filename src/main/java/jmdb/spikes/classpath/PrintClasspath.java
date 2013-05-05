package jmdb.spikes.classpath;

import java.net.URL;
import java.net.URLClassLoader;

public class PrintClasspath {
    public static void main(String[] args) {
        StringBuilder out = new StringBuilder();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        addClassLoaderUrl(cl, out);

        System.out.println(out.toString());

    }

    private static void addClassLoaderUrl(ClassLoader cl, StringBuilder out) {
        if (!(cl instanceof URLClassLoader)) {
            return;
        }

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for (URL url : urls) {
            out.append(url.toExternalForm()).append(":\n");
        }

        if (cl.getParent() != null) {
            addClassLoaderUrl(cl.getParent(), out);
        }
    }
}