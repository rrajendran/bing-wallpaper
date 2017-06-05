package unit.com.capella.bing.wallpaper.service;

import com.capella.bing.wallpaper.service.BingImageService;
import com.capella.bing.wallpaper.service.BingImageServiceImpl;
import org.junit.Test;

/**
 * @author Ramesh Rajendran
 */
public class BingImageServiceImplTest {
    private BingImageService bingImageService = new BingImageServiceImpl();

    @Test
    public void todaysImage() throws Exception {
        bingImageService.todaysImage("/tmp/bing/new");
    }

}