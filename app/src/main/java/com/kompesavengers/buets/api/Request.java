package com.kompesavengers.buets.api;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by triforce on 12/04/15.
 */
public abstract class Request {

    public static final String API_URL_PREFIX = "http://www.yigitozkavci.com/buets/api/v1/";

    protected RequestCallback callback;

    private ArrayList<String> parameters = new ArrayList<>();

    public abstract void execute();

    public abstract String buildRequestURL();

    protected JSONArray startRequest(String urlString) throws IOException, JSONException {
        URL url = new URL(urlString);

        InputStream stream = url.openConnection().getInputStream();

        String result = convertInputStreamToString(stream);

        JSONArray json = new JSONArray(result);

        return json;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    public Request setCallback(RequestCallback callback) {
        this.callback = callback;
        return this;
    }

    public interface RequestCallback{
        public void onRequest();
        public void onResult(int errorCode, String errorString, ArrayList array);
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public Request addParameter(String param)
    {
        this.getParameters().add(param);
        return this;
    }
}
