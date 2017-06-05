package com.capella.bing.wallpaper;

import com.capella.bing.wallpaper.service.BingImageServiceImpl;

/**
 * @author Ramesh Rajendran
 */
public class MainApp {

    public static void main(String[] args) throws Exception {
        String downloadLocation = System.getProperty("user.home") + "/bing-images/";
        if (args.length > 0) {
            downloadLocation = args[0];
        }
        new BingImageServiceImpl().todaysImage(downloadLocation);
    }
}
