package com.capella.bing.wallpaper.service;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

import java.io.File;
import java.util.function.Consumer;

/**
 * Created by ramesh on 05/06/2017.
 */
public class WindowsDesktopChanger implements Consumer<File> {


    /**
     * Performs this operation on the given argument.
     *
     * @param file the input argument
     */
    @Override
    public void accept(File file) {
        boolean changed = Wallpaper.User32.INSTANCE.SystemParametersInfo(0x0014, 0, file.getAbsolutePath(), 1);
        if (!changed) {
            throw new IllegalStateException("Unable to change wallpaper");
        }
    }

    public interface User32 extends Library {
        Wallpaper.User32 INSTANCE = (Wallpaper.User32) Native.loadLibrary("user32", Wallpaper.User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }
}
