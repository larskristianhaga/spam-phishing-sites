package no.haga;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class findHiddenEndpointWithID {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder().build();

    public static void main(String[] args) {
        findHiddenEndpointWithID();
    }

    private static void findHiddenEndpointWithID() {
        var baseUrl = "";
        var maxAttempts = 1_000_000_000;
        var numberOfThreads = 10;
        var range = maxAttempts / numberOfThreads;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int t = 0; t < numberOfThreads; t++) {
            int finalT = t;
            executor.submit(() -> {
                try {
                    System.out.println(STR."Thread \{finalT} started");
                    findURL(baseUrl, range * finalT, range * (finalT + 1));
                } catch (Exception e) {
                    System.out.println(STR."Thread \{finalT} encountered an exception: \{e.getMessage()}, stacktrace: \{e.getStackTrace()}");
                }
            });
        }
        executor.shutdown();
        System.out.println("All threads finished.");
    }

    @SneakyThrows
    private static void findURL(String baseUrl, int start, int end) {
        int i = start;
        while (i < end) {
            // JSON body
            var jsonBody = STR."""
                    {
                      "id": \{i}
                    }
                    """;

            // Overwrite with Postman export
            var mediaType = MediaType.parse("application/json;charset=UTF-8");
            var body = RequestBody.create(mediaType, jsonBody);
            var request = new Request.Builder().url(baseUrl).method("POST", body)
                    .build();

            // Send the request
            var response = CLIENT.newCall(request).execute();

            // Checks if we get anything else than 404
            if (response.code() != HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(STR."Found ID: \{i}");
                break;
            }

            response.close();
            i++;
        }
    }
}