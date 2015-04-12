package com.kompesavengers.buets.api;

import com.kompesavengers.buets.model.Event;
import com.kompesavengers.buets.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by triforce on 12/04/15.
 */
public class PlacesRequest extends Request {

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONArray arr = startRequest(buildRequestURL());

                    ArrayList<Place> places = new ArrayList<>();

                    for(int i = 0; i < arr.length(); i++)
                    {
                        JSONObject current = arr.getJSONObject(i);
                        Place p = new Place();

                        p.setId(current.getInt("id"));
                        p.setName(current.getString("name"));
                        String[] latnlong = current.getString("coordinates").split(", ");
                        String lat = latnlong[0];
                        String lon = latnlong[1];
                        p.setCoordLat(Float.valueOf(lat));
                        p.setCoordLong(Float.valueOf(lon));

                        places.add(p);
                    }

                    if(PlacesRequest.this.callback != null)
                    {
                        callback.onResult(0, "", places);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onResult(-1, "IOException", null);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onResult(-1, "JSONException", null);
                }
            }
        }).start();
    }

    @Override
    public String buildRequestURL() {
        StringBuilder str = new StringBuilder();

        str.append(this.API_URL_PREFIX);
        str.append("places/");
        for(String s : getParameters())
        {
            str.append(s + "/");
        }
        return str.toString();
    }

}
