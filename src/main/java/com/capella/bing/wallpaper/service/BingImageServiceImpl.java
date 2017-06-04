package com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.client.BingClient;
import com.capella.bing.wallpaper.domain.BingImage;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImpl implements BingImageService {
    private BingClient bingClient = new BingClient();

    @Override
    public void todaysImage() throws Exception {
        BingImage photoOfTheDay = bingClient.getPhotoOfTheDay();

        photoOfTheDay.getImages().stream().forEach(image -> {
            try {
                downloadImage("http://www.bing.com" + image.getUrl(), "/tmp/bing/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    public static void downloadImage(String sourceUrl, String targetDirectory)
            throws IOException {
        System.out.println("Downloading - " + sourceUrl);
        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(
                imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(targetDirectory + File.separator
                             + FilenameUtils.getName(sourceUrl)))) {
            int readByte;

            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        }
    }
}
