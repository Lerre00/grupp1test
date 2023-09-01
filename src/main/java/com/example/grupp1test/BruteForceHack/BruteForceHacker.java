package com.example.grupp1test.BruteForceHack;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BruteForceHacker {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/home"); // Update with your server URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create a sample user JSON
            String userJson = "{\"username\": \"user123\", \"email\": \"user@example.com\"}";

            // Write the user JSON data to the request
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(userJson);
                outputStream.flush();
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



