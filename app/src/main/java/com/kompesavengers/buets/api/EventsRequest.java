package com.kompesavengers.buets.api;

import com.kompesavengers.buets.model.Event;
import com.kompesavengers.buets.model.Organizer;
import com.kompesavengers.buets.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by triforce on 12/04/15.
 */
public class EventsRequest extends Request {
    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONArray arr = startRequest(buildRequestURL());

                    ArrayList<Event> events = new ArrayList<>();

                    for(int i = 0; i < arr.length(); i++)
                    {
                        JSONObject current = arr.getJSONObject(i);
                        Event e = new Event();

                        e.setId(current.getInt("id"));
                        e.setName(current.getString("name"));
                        e.setDetail(current.getString("description"));
                        e.setOrganizerId(current.getInt("organizer_id"));
                        e.setPlaceId(current.getInt("place_id"));
                        e.setLogo_link(current.getString("logo_name"));
                        e.setBanner_link(current.getString("cover_name"));
                        e.setStartDate(current.getString("start_date"));
                        e.setEndDate(current.getString("end_date"));
                        e.setUrl(current.getString("url"));

                        events.add(e);
                    }

                    if(EventsRequest.this.callback != null)
                    {
                        callback.onResult(0, "", events);
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
        str.append("events/");
        for(String s : getParameters())
        {
            str.append(s + "/");
        }
        return str.toString();
    }

    private Place getPlace(int placeId) throws IOException, JSONException {
        JSONArray arr = startRequest(buildRequestURL());

        ArrayList<Place> places = new ArrayList<>();

        for(int i = 0; i < arr.length(); i++)
        {
            JSONObject current = arr.getJSONObject(i);
            Place p = new Place();

            p.setId(current.getInt("id"));
            p.setName(current.getString("name"));
            //TODO

            places.add(p);
        }
        return null;
    }

    private Organizer getOrganizer(int orgId)
    {
        return null;
    }
}

