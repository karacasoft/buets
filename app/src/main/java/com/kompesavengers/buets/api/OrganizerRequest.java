package com.kompesavengers.buets.api;

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
public class OrganizerRequest extends Request{

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONArray arr = startRequest(buildRequestURL());

                    ArrayList<Organizer> organizers = new ArrayList<>();

                    for(int i = 0; i < arr.length(); i++)
                    {
                        JSONObject current = arr.getJSONObject(i);
                        Organizer o = new Organizer();

                        o.setId(current.getInt("id"));
                        o.setName(current.getString("name"));
                        //TODO

                        organizers.add(o);
                    }

                    if(OrganizerRequest.this.callback != null)
                    {
                        callback.onResult(0, "", organizers);
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
        str.append("organizators/");
        for(String s : getParameters())
        {
            str.append(s + "/");
        }
        return str.toString();
    }

}
