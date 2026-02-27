package com.ameshajid.mutualfund.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ameshajid.mutualfund.model.NewtonBetaApiResponse;

@Service
public class NewtonBetaService {

    private static final String BASE_URL = "https://api.newtonanalytics.com/stock-beta/";
    private final RestTemplate restTemplate = new RestTemplate();

    public double getBeta(String ticker, String index, String interval, int observations) {

        if (ticker == null || ticker.trim().isEmpty()) {
            throw new IllegalArgumentException("ticker is required");
        }

        if (index == null || index.trim().isEmpty()) {
            index = "^GSPC";
        }

        if (interval == null || interval.trim().isEmpty()) {
            interval = "1mo";
        }

        if (observations <= 0) {
            observations = 10;
        }

        String tickerEncoded = URLEncoder.encode(ticker.trim(), StandardCharsets.UTF_8);
        String indexEncoded = URLEncoder.encode(index.trim(), StandardCharsets.UTF_8);
        String intervalEncoded = URLEncoder.encode(interval.trim(), StandardCharsets.UTF_8);

        String url = BASE_URL
                + "?ticker=" + tickerEncoded
                + "&index=" + indexEncoded
                + "&interval=" + intervalEncoded
                + "&observations=" + observations;

        NewtonBetaApiResponse response = restTemplate.getForObject(url, NewtonBetaApiResponse.class);

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Newton API returned no beta data");
        }

        return response.getData();
    }
}