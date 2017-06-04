package com.capella.bing.wallpaper.exceptions;

import java.util.concurrent.Callable;

/**
 * @author Ramesh Rajendran
 */
public class Exceptions {
    /**
     * @param o
     */
    public static void uncheck(Callable<?> o) {
        try {
            o.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
