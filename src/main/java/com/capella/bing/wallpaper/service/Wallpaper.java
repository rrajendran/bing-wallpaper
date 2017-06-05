package com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.utils.OsCheck;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

import java.io.File;
import java.io.IOException;

public class Wallpaper {

    public static void changeDesktopWallpaper(File wallpaperFile) throws IOException {
        System.out.println("Changing desktop wallpaper : " + wallpaperFile.getAbsolutePath());
        if (!wallpaperFile.exists()) {
            throw new IllegalArgumentException("Unable to read wallpaper");
        }
        OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
        switch (ostype) {
            case Windows:
                boolean changed = User32.INSTANCE.SystemParametersInfo(0x0014, 0, wallpaperFile.getAbsolutePath(), 1);
                if (!changed) {
                    throw new IllegalStateException("Unable to change wallpaper");
                }
                break;
            case MacOS:
                String as[] = {
                        "osascript",
                        "-e", "tell application \"Finder\"",
                        "-e", "set desktop picture to POSIX file \"" + wallpaperFile.getAbsolutePath() + "\"",
                        "-e", "end tell"
                };
                Runtime.getRuntime().exec(as);
                break;
            case Linux:
                break;
            case Other:
                break;
        }


    }

    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }

}