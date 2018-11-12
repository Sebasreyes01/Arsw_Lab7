package com.edu.eci.arsw.bd_nosql.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class StockMarketService {

    private static final String USER_AGENT = "Mozilla/5.0";
//    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";
    private static final String SITE = "https://www.alphavantage.co/query?function=";
    private static final String SYMBOL = "&symbol=";
    private static final String INTERVAL = "&interval=";
    private static final String KEY = "&apikey=Q1QZFVJQ21K7C6XM";

    public String stockInfo(String function, String symbol, String interval) throws IOException {

//        URL obj = new URL(GET_URL);
        URL obj = new URL(SITE + function + SYMBOL + symbol + INTERVAL + interval + KEY);
//        System.out.println("url: " + SITE + function + SYMBOL + symbol + INTERVAL + interval + KEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "GET request not worked";
        }
//        System.out.println("GET DONE");
    }

}
