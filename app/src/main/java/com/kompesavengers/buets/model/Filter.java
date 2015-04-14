package com.kompesavengers.buets.model;

import java.util.ArrayList;

/**
 * Created by Irmak on 12.4.2015.
 */
public class Filter {
    private boolean startDateFilterActive;
    private boolean endDateFilterActive;

    private DateFilter dateFilter = new DateFilter();

    private ArrayList<Tag> tags = new ArrayList<>();

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

    public DateFilter getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}