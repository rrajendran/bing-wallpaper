package com.capella.bing.wallpaper.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import com.capella.bing.wallpaper.service.BingImageServiceImpl;

import static org.slf4j.LoggerFactory.getLogger;

public class DownloadImageJob implements Job {
    public static final Logger LOGGER = getLogger(DownloadImageJob.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        String downloadLocation = "/tmp/bingimages";
        System.out.println("Download location = " + downloadLocation);
        try {
            new BingImageServiceImpl().todaysImage(downloadLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}