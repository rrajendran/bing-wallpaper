package unit.com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.service.BingImageService;
import com.capella.bing.wallpaper.service.BingImageServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImplTest {
    private BingImageService bingImageService = new BingImageServiceImpl();

    @Test
    public void todaysImage() throws Exception {
        String downloadLocation = System.getProperty("user.home") + "/bing-images/";
        bingImageService.todaysImage(downloadLocation);

        assertThat(new File(downloadLocation).listFiles().length > 0, CoreMatchers.is(true));
    }


    @Test
    public void testArchive() throws Exception {
        bingImageService.archive(10, System.getProperty("user.home") + "/bing-images/");
    }

}