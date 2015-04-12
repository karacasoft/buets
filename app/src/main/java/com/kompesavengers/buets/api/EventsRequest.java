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
                        e.setOrganizerId(current.getInt("organizator_id"));
                        e.setOrganizer(getOrganizer(e.getOrganizerId()));
                        e.setPlaceId(current.getInt("place_id"));
                        e.setPlace(getPlace(e.getPlaceId()));
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
        JSONObject current = startRequestJSONObject(buildPlaceRequestURL(placeId));
        Place p = new Place();

        p.setId(current.getInt("id"));
        p.setName(current.getString("name"));
        String[] latnlong = current.getString("coordinates").split(", ");
        String lat = latnlong[0];
        String lon = latnlong[1];
        p.setCoordLat(Float.valueOf(lat));
        p.setCoordLong(Float.valueOf(lon));

        return p;
    }

    public String buildPlaceRequestURL(int id) {
        StringBuilder str = new StringBuilder();

        str.append(this.API_URL_PREFIX);
        str.append("places/");
        str.append(id + "/");
        return str.toString();
    }

    private Organizer getOrganizer(int orgId) throws IOException, JSONException {
        JSONObject current = startRequestJSONObject(buildOrganizerRequestURL(orgId));

        Organizer o = new Organizer();

        o.setId(current.getInt("id"));
        o.setName(current.getString("name"));
        o.setLogoName(current.getString("logo_name"));

        return o;
    }

    public String buildOrganizerRequestURL(int id) {
        StringBuilder str = new StringBuilder();

        str.append(this.API_URL_PREFIX);
        str.append("organizators/");
        str.append(id + "/");
        return str.toString();
    }


}

