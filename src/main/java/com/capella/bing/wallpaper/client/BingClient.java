package com.capella.bing.wallpaper.client;

import com.capella.bing.wallpaper.domain.BingImage;
import com.capella.bing.wallpaper.utils.JsonHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Ramesh Rajendran
 */
public class BingClient {
    public static final Logger LOGGER = getLogger(BingClient.class);
    private final OkHttpClient client = new OkHttpClient();

    public BingImage getPhotoOfTheDay(final String locale) throws Exception {
        String url = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=" + locale;
        LOGGER.info("Ïmage url:" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return JsonHelper.jsonToObject(response.body().byteStream(), BingImage.class);
    }
}
