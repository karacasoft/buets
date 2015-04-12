package com.kompesavengers.buets.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.kompesavengers.buets.MainActivity;
import com.kompesavengers.buets.R;
import com.kompesavengers.buets.model.Filter;
import com.kompesavengers.buets.model.Tag;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {

    private LinearLayout tagList;
    private Filter filter = new Filter();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FilterFragment.
     */
    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        return fragment;
    }

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        DatePicker startDatePicker = (DatePicker) v.findViewById(R.id.filterDateStart);
        DatePicker endDatePicker = (DatePicker) v.findViewById(R.id.filterDateEnd);
        Button filtersAreOkButton = (Button) v.findViewById(R.id.filters_are_ok_button);

        //TODO change date to today's date
        startDatePicker.init(2015, 04, 12, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                filter.getDate().setStartYear(year);
                filter.getDate().setStartMonth(monthOfYear);
                filter.getDate().setStartDay(dayOfMonth);
            }
        });

        endDatePicker.init(2015, 04, 12, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                filter.getDate().setEndYear(year);
                filter.getDate().setEndMonth(monthOfYear);
                filter.getDate().setEndDay(dayOfMonth);
            }
        });

        tagList = (LinearLayout) v.findViewById(R.id.tags_layout);

        listTags(((MainActivity)getActivity()).getTags());

        filtersAreOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).filterEvents(filter);
            }
        });

        return v;
    }

    private void listTags(ArrayList<Tag> tags)
    {
        for(final Tag t : tags)
        {
            View v = LayoutInflater.from(getActivity()).inflate(
                    R.layout.list_item_tag, tagList, false);

            CheckBox tag_name = (CheckBox) v.findViewById(R.id.tag_list_name);
            tag_name.setText(t.getName());

            tag_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        filter.getTags().add(t);
                    }else{
                        filter.getTags().remove(t);
                    }
                }
            });

            tagList.addView(v);

        }
    }

}
