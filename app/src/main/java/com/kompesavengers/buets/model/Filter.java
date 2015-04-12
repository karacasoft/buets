package com.kompesavengers.buets.model;

import java.util.ArrayList;

/**
 * Created by Irmak on 12.4.2015.
 */
public class Filter {
    Date date;

    ArrayList<Tag> tags;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}