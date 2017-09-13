package com.capella.bing.wallpaper.scheduler;

import com.capella.bing.wallpaper.service.BingImageServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class DownloadImageJob implements Job {
    public static final Logger LOGGER = getLogger(DownloadImageJob.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        String downloadLocation = System.getProperty("bing.home");
        LOGGER.info("Download location = " + downloadLocation);
        try {
            new BingImageServiceImpl().todaysImage(downloadLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}