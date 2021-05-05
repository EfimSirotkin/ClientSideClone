package sample.http;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class RequestGenerator {

    public static String serverIPport = "localhost:8001";

    public HttpRequest request;

    public RequestGenerator() {}

    public String getResponseResults() {

        String result = null;
        try {
            CompletableFuture<HttpResponse<String>> response = CustomHttpClientSingleton.getInstance().sendAsync(request, HttpResponse.BodyHandlers.ofString());
            result = response.thenApply(HttpResponse::body).join();
        } catch(RuntimeException e) {
            System.out.println("Seems like the server may not be working properly! Restart it");
        }
        return result;
    }


}
