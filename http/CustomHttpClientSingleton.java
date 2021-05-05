package sample.http;

import java.net.http.HttpClient;

public final class CustomHttpClientSingleton {

    private static CustomHttpClientSingleton clientInstance;
    private static HttpClient customClient;

    public CustomHttpClientSingleton() {
        customClient = HttpClient.newHttpClient();
    }

    public static HttpClient getInstance() {
        if(customClient == null)
            customClient = HttpClient.newHttpClient();
        return customClient;
    }

}
