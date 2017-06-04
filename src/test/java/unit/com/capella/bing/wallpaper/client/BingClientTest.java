package unit.com.capella.bing.wallpaper.client;

import com.capella.bing.wallpaper.client.BingClient;
import com.capella.bing.wallpaper.domain.BingImage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Ramesh Rajendran
 */
public class BingClientTest {
    BingClient client = new BingClient();

    @Test
    public void getPhotoOfTheDay() throws Exception {

        BingImage photoOfTheDay = client.getPhotoOfTheDay();

        assertThat(photoOfTheDay, is(notNullValue()));
        System.out.println(photoOfTheDay.getImages().get(0).getUrl());
        assertThat(photoOfTheDay.getImages().get(0).getUrl(), is(notNullValue()));
    }

}