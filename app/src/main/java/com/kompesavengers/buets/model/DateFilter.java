package com.kompesavengers.buets.model;

/**
 * Created by Irmak on 12.4.2015.
 */
public class DateFilter {

    int startDay, startMonth, startYear;
    int endDay, endMonth, endYear;

    public DateFilter()
    {}

    public static DateFilter parseFromStrings(String startDate, String endDate)
    {
        DateFilter filter = new DateFilter();
        filter.startDay = Integer.parseInt(startDate.substring(8,10));
        filter.startMonth = Integer.parseInt(startDate.substring(5,7));
        filter.startYear = Integer.parseInt(startDate.substring(0,4));
        filter.endDay = Integer.parseInt(endDate.substring(8,10));
        filter.endMonth = Integer.parseInt(endDate.substring(5,7));
        filter.endYear = Integer.parseInt(endDate.substring(0,4));
        return filter;
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
