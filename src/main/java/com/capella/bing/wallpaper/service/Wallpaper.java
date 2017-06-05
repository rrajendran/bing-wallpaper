package com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.utils.OsCheck;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
                new WindowsDesktopChanger().accept(wallpaperFile);
                break;
            case MacOS:
                new MacosxDesktopChanger().accept(wallpaperFile);
                break;
            case Linux:
                throw new NotImplementedException();
            case Other:
                break;
        }


    }

    public interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }

}