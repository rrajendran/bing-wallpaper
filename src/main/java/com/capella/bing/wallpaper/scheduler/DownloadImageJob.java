package com.capella.bing.wallpaper.scheduler;

import com.capella.bing.wallpaper.service.BingImageServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DownloadImageJob implements Job {


    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        String downloadLocation = System.getProperty("user.home") + "/bing-images/";

        try {
            new BingImageServiceImpl().todaysImage(downloadLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}