package com.capella.bing.wallpaper;

import com.capella.bing.wallpaper.scheduler.DownloadScheduler;

/**
 * @author Ramesh Rajendran
 */
public class MainApp {

    public static void main(String[] args) throws Exception {
        DownloadScheduler schedulerExample = new DownloadScheduler();
        String cronExpression = "* * * * * ?";
        if (args.length != 0) {
            cronExpression = args[0];
        }
        schedulerExample.downloadTodaysPhoto(cronExpression);
    }
}
