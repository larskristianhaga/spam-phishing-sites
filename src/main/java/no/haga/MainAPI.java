package no.haga;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.java.Log;

@Log
public class MainAPI {

    private static final String API_ENDPOINT = "";
    private static final String BODY = """
            
            """;

    public static void main(String[] args) {
        log.info("Starting API program...");

        while (true) {
            // doGETAPICall();
            // doPOSTAPICall();
        }
    }

    private static void doGETAPICall() {
        log.info("Doing GET API call...");
        try {
            Thread.sleep(100);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MainAPI.API_ENDPOINT))
                    .header("User-Agent", "Haz")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("API call done.");
        } catch (InterruptedException e) {
            log.severe("API call interrupted.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.severe("API call failed.");
            throw new RuntimeException(e);
        }
    }

    private static void doPOSTAPICall() {
        log.info("Doing POST API call...");
        try {
            Thread.sleep(100);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.BodyPublishers.ofString("");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MainAPI.API_ENDPOINT))
                    .header("User-Agent", "Haz")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(MainAPI.BODY))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("API call done.");
        } catch (InterruptedException e) {
            log.severe("API call interrupted.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.severe("API call failed.");
            throw new RuntimeException(e);
        }
    }
}