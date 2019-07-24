package com.esgi.projetjee.api;

import com.esgi.projetjee.utils.http.FullResponseBuilder;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhqApi {

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;

    private static final String BASE_URL = "https://api.predicthq.com/";
    private static final String EVENTS_URL = BASE_URL + "v1/events/";

    // Never expires
    private static final String ACCESS_TOKEN = "JAHEDwJbdhtwyPXy7mtgMz1cYkWXMx";

    private static final String RESPONSE_KEY_IN_FULL_RESPONSE = "Response: ";


    public JSONObject findEvents() throws IOException {
        URL url = new URL(EVENTS_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");


        /*Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();*/

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);

        con.setConnectTimeout(CONNECT_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);

        String fullResponse = FullResponseBuilder.getFullResponse(con);
        return getBodyFromFullResponse(fullResponse);
    }

    private JSONObject getBodyFromFullResponse(String fullResponse) {
        int responseKeyIndex = fullResponse.indexOf(RESPONSE_KEY_IN_FULL_RESPONSE);
        String bodyJson = fullResponse.substring(responseKeyIndex + RESPONSE_KEY_IN_FULL_RESPONSE.length());

        return new Gson().fromJson(bodyJson, JSONObject.class);
    }
}
