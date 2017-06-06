package com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.client.BingClient;
import com.capella.bing.wallpaper.domain.BingImage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;
import java.util.stream.Stream;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImpl implements BingImageService {
    private BingClient bingClient = new BingClient();
    private String[] locales = new String[]{"en-US", "zh-CN", "ja-JP", "en-AU", "en-UK", "de-DE", "en-NZ"};

    @Override
    public void todaysImage(String downloadLocation) throws Exception {
        Stream.of(locales).forEach(locale -> {
            try {
                download(downloadLocation, locale.toString().replaceAll("_", "-"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void download(String downloadLocation, String locale) throws Exception {
        BingImage photoOfTheDay = bingClient.getPhotoOfTheDay(locale);

        photoOfTheDay.getImages().stream().forEach(image -> {
            try {
                File savedFile = downloadImage("http://www.bing.com" + image.getUrl(), downloadLocation);
                Wallpaper.changeDesktopWallpaper(savedFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public static File downloadImage(String sourceUrl, String targetDirectory)
            throws IOException, InterruptedException {
        System.out.println("Downloading - " + sourceUrl);
        File directory = new File(targetDirectory);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }

        URL imageUrl = new URL(sourceUrl);
        InputStream imageReader = new BufferedInputStream(
                imageUrl.openStream());
        File filePath = new File(targetDirectory + File.separator
                + FilenameUtils.getName(sourceUrl));

        if (!filePath.exists()) {
            OutputStream imageWriter = new BufferedOutputStream(
                    new FileOutputStream(filePath));
            int readByte;

            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }

            imageWriter.close();
        }

        return filePath;
    }
}
