/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.h2.util.json.JSONObject;

/**
 *
 * @author guilherme.nsantos
 */
public class Slack {

    private static HttpClient client = HttpClient.newHttpClient();
    private static final String url = "https://hooks.slack.com/services/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";

//    public static void enviarMensagem(JSONObject content) throws IOException, InterruptedException {;
//
//        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
//                .header("accept", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(String.format("Status: %s", response.statusCode()));
//        System.out.println(String.format("Response: %s", response.body()));
//    }

    static void enviarMensagem(org.json.JSONObject json) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(String.format("Status: %s", response.statusCode()));
        System.out.println(String.format("Response: %s", response.body()));
    }
}
