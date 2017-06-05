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

    public BingImage getPhotoOfTheDay() throws Exception {
        Request request = new Request.Builder()
                .url("http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-GB")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return JsonHelper.jsonToObject(response.body().byteStream(), BingImage.class);
    }
}
