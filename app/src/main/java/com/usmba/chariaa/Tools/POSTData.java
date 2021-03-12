package com.usmba.chariaa.Tools;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class POSTData {

    public static String getJSONString(String url, JSONObject parameters) throws JSONException, IOException {
        String request = "";
        for (int i = 0; i < parameters.names().length() ; i++) {
            String key = parameters.names().getString(i);
            String value = parameters.getString(key);
            request += "&";
            request += URLEncoder.encode(key,"UTF-8");
            request += "=";
            request += URLEncoder.encode(value,"UTF-8");
        }
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setReadTimeout(1000);
        urlConnection.setConnectTimeout(1000);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
        outputStreamWriter.write(request.substring(1));
        outputStreamWriter.flush();
        outputStreamWriter.close();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

}
