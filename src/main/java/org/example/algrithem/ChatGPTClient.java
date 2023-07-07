package org.example.algrithem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ChatGPTClient {
    private static final String API_KEY = "Bearer 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";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws IOException, URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(new URI(API_ENDPOINT));
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("prompt", "Hello, how are you?"));
        params.add(new BasicNameValuePair("max_tokens", "60"));
        params.add(new BasicNameValuePair("temperature", "0.5"));
        params.add(new BasicNameValuePair("n", "1"));
        params.add(new BasicNameValuePair("stop", "."));
        httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        System.out.println(responseString);
    }
}