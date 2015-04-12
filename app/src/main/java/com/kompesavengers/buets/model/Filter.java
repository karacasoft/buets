package com.kompesavengers.buets.model;

import java.util.ArrayList;

/**
 * Created by Irmak on 12.4.2015.
 */
public class Filter {
    private boolean startDateFilterActive;
    private boolean endDateFilterActive;

    public boolean isStartDateFilterActive() {
        return startDateFilterActive;
    }

    public void setStartDateFilterActive(boolean startDateFilterActive) {
        this.startDateFilterActive = startDateFilterActive;
    }

    public boolean isEndDateFilterActive() {
        return endDateFilterActive;
    }

    public void setEndDateFilterActive(boolean endDateFilterActive) {
        this.endDateFilterActive = endDateFilterActive;
    }

    Date date = new Date();

    ArrayList<Tag> tags = new ArrayList<>();

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