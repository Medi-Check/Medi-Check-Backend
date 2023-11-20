package com.example.medicheckbackend.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.stereotype.Service;
import okhttp3.OkHttpClient;

@RequiredArgsConstructor
@Service
public class LEDService {

    public void OnLED(int num) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.get("http://yuno.hopto.org:5000/led-on").newBuilder();
        httpBuilder.addQueryParameter("led", String.valueOf(num));

        Request request = new Request.Builder()
                .get()
                .url(httpBuilder.build())
                .build();

        client.newCall(request).execute();
    }

    public void OffLED() throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.get("http://yuno.hopto.org:5000/led-off").newBuilder();
        httpBuilder.addQueryParameter("led", String.valueOf(15));

        Request request = new Request.Builder()
                .get()
                .url(httpBuilder.build())
                .build();

        client.newCall(request).execute();
    }

}
