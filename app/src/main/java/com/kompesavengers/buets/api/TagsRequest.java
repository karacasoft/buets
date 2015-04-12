package com.kompesavengers.buets.api;

import com.kompesavengers.buets.model.Tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by triforce on 12/04/15.
 */
public class TagsRequest extends Request{

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONArray arr = startRequest(buildRequestURL());

                    ArrayList<Tag> tags = new ArrayList<Tag>();

                    for(int i = 0; i < arr.length(); i++)
                    {
                        JSONObject current = arr.getJSONObject(i);
                        Tag t = new Tag();
                        t.setId(current.getInt("id"));
                        t.setName(current.getString("name"));
                        tags.add(t);
                    }

                    if(TagsRequest.this.callback != null)
                    {
                        callback.onResult(0, "", tags);
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
        str.append("tags/");
        for(String s : getParameters())
        {
            str.append(s + "/");
        }
        return str.toString();
    }
}
