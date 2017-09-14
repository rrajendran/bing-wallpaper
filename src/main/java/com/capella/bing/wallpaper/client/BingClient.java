package com.capella.bing.wallpaper.client;

import com.capella.bing.wallpaper.domain.BingImage;
import com.capella.bing.wallpaper.utils.JsonHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Ramesh Rajendran
 */
public class BingClient {
    private final OkHttpClient client = new OkHttpClient();

    public BingImage getPhotoOfTheDay(final String locale, final int count, final int index) throws Exception {
        String url = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=" + index + "&n=" + count + "&mkt=" + locale;
        System.out.println("Ïmage url:" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return JsonHelper.jsonToObject(response.body().byteStream(), BingImage.class);
    }
}
