package selenium.bing;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebDriver;
import selenium.SearchPage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ramesh Rajendran
 */
public class BingDownloadPage implements SearchPage {
    private WebDriver driver;
    private static final String PATTERN = "background(-image)?: url[\\s]*\\([\\s]*(?<url>[^\\)]*)\\);";

    @Override
    public void init(final WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void download(LocalDate localDate) {
        /*//Exceptions.uncheck(() -> Thread.sleep(1000l));

        try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(driver.getPageSource());


        Element elementsByClass = document.getElementById("bgDiv");
        String attr = elementsByClass.attr("style");

        String imageUrl = attr.substring(attr.indexOf("https://"), attr.indexOf("\")"));
        System.out.println(imageUrl);

        try {
            downloadImage(imageUrl, "/tmp/bing/");
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    public static void downloadImage(String sourceUrl, String targetDirectory)
            throws IOException {
        URL imageUrl = new URL(sourceUrl);
        try (InputStream imageReader = new BufferedInputStream(
                imageUrl.openStream());
             OutputStream imageWriter = new BufferedOutputStream(
                     new FileOutputStream(targetDirectory + File.separator
                             + FilenameUtils.getName(sourceUrl)));) {
            int readByte;

            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        }
    }

    private boolean match(String input) {
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(input);
        return m.matches();
    }
}