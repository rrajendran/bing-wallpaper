package unit.com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.service.BingImageService;
import com.capella.bing.wallpaper.service.BingImageServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImplTest {
    private BingImageService bingImageService = new BingImageServiceImpl();

    @Test
    public void todaysImage() throws Exception {
        String downloadLocation = "/tmp/bing";
        bingImageService.todaysImage(downloadLocation);

        assertThat(new File(downloadLocation).listFiles().length > 0, CoreMatchers.is(true));
    }

}