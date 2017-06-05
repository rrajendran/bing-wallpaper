package com.capella.bing.wallpaper.scheduler;

import org.quartz.*;
import org.slf4j.Logger;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;


public class DownloadScheduler {
    public static final Logger LOGGER = getLogger(DownloadScheduler.class);

    /**
     * @param cronExpression
     * @throws SchedulerException
     * @throws InterruptedException
     */
    public void downloadTodaysPhoto(String cronExpression) throws SchedulerException, InterruptedException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();
        scheduler.start();

        // define the job and tie it to our DownloadImageJob class
        JobBuilder jobBuilder = JobBuilder.newJob(DownloadImageJob.class);
        JobDataMap data = new JobDataMap();
        data.put("latch", this);

        JobDetail jobDetail = jobBuilder.usingJobData("BingPhoto", "com.capella.bing.wallpaper.scheduler.DownloadScheduler")
                .usingJobData(data)
                .withIdentity("BingPhotoJob", "group1")
                .build();


        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("BingPhotoTrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronExpression))
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("Bing daily photo started at :" + LocalDateTime.now());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Scheduler exited");
            try {
                scheduler.shutdown();
            } catch (SchedulerException e) {
                LOGGER.error("Scheduler exception -", e);
            }
        }) {
        });
    }

}