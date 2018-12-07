package com.capella.bing.wallpaper.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.capella.bing.wallpaper.client.BingClient;
import com.capella.bing.wallpaper.domain.BingImage;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifImageDirectory;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImpl implements BingImageService {
    private BingClient bingClient = new BingClient();
    private String[] locales = new String[]{"en-UK", "en-US", "zh-CN", "en-CA", "en-AU", "de-DE", "fr-FR", "en-NZ"};

    @Override
    public void todaysImage(String downloadLocation) throws Exception {
        String dateFolder = LocalDate.now().minusDays(new Long(0)).toString();
        Stream.of(locales).forEach(locale -> {
            try {
                download(downloadLocation + "/" + dateFolder, locale.toString().replaceAll("_", "-"), 1, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    @Override
    public void archive(Integer daysOld, String downloadLocation) throws Exception {
        IntStream.range(0, daysOld).forEach(day -> Stream.of(locales).forEach(locale -> {
            try {
                String dateFolder = LocalDate.now().minusDays(new Long(day)).toString();
                download(downloadLocation + "/" + dateFolder,
                        locale.toString().replaceAll("_", "-"), 1, day);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));



    }

    private void download(String downloadLocation, String locale, int count, int index) throws Exception {
        BingImage photoOfTheDay = bingClient.getPhotoOfTheDay(locale, count, index);

        photoOfTheDay.getImages().stream().forEach(image -> {
            try {
                File savedFile = downloadImage("http://www.bing.com" + image.getUrl(), downloadLocation, image.getCopyright());
                //Wallpaper.changeDesktopWallpaper(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void addExif(byte[] bytes) throws ImageProcessingException, IOException {
        Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());
        Metadata metadata = JpegMetadataReader.readMetadata(new ByteArrayInputStream(bytes));
        ExifImageDirectory dir = new ExifImageDirectory();

        metadata.addDirectory(dir);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
    }




    public static File downloadImage(String sourceUrl, String targetDirectory, String imageDescription)
            throws IOException, InterruptedException, ImageProcessingException {
        System.out.println("Downloading - " + sourceUrl);
        File directory = new File(targetDirectory);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }

        URL imageUrl = new URL(sourceUrl);
        //byte[] bytes = IOUtils.toByteArray(imageUrl);
        //addExif(bytes);

        BufferedImage bufferedImage = ImageIO.read(imageUrl); //ImageHelper.writeText(new ByteArrayInputStream(bytes), imageDescription);

        String pathname = targetDirectory + File.separator + FilenameUtils.getName(sourceUrl);
        System.out.println("Saving at " + pathname);
        File filePath = new File(pathname);
        filePath.mkdirs();

        ImageIO.write(bufferedImage, "jpg", filePath);

        //addExifMetaData(filePath,FilenameUtils.getName(pathname));
        return filePath;
    }
}
