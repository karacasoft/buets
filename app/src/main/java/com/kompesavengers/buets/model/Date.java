package com.kompesavengers.buets.model;

import com.google.android.gms.location.places.AutocompletePrediction;

/**
 * Created by Irmak on 12.4.2015.
 */
public class Date {

    int startDay, startMonth, startYear;
    int endDay, endMonth, endYear;

    public Date(String startDate, String endDate){
        startDay = Integer.parseInt(startDate.substring(0,2));
        startMonth = Integer.parseInt(startDate.substring(3,5));
        startYear = Integer.parseInt(startDate.substring(6,10));
        endDay = Integer.parseInt(startDate.substring(0,2));
        endMonth = Integer.parseInt(startDate.substring(3,5));
        endYear = Integer.parseInt(startDate.substring(6,10));
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
