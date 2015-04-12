package com.kompesavengers.buets.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
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

        final DatePicker startDatePicker = new DatePicker(getActivity());
        final DatePicker endDatePicker = new DatePicker(getActivity());

        if(Build.VERSION.SDK_INT >= 11) {
            startDatePicker.setCalendarViewShown(false);
            endDatePicker.setCalendarViewShown(false);
        }

        final Button startDatePickerButton = (Button) v.findViewById(R.id.filterStartDateButton);
        final Button endDatePickerButton = (Button) v.findViewById(R.id.filterEndDateButton);

        Button filtersAreOkButton = (Button) v.findViewById(R.id.filters_are_ok_button);

        final AlertDialog.Builder builderstart = new AlertDialog.Builder(getActivity());
        builderstart.setTitle("Başlangıç Tarihi Seç")
                .setView(startDatePicker)
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filter.setStartDateFilterActive(true);
                        startDatePickerButton.setText("Başlangıç Tarihi: " +
                                startDatePicker.getYear() + "-" + (startDatePicker.getMonth() + 1)
                                + "-" + startDatePicker.getDayOfMonth());
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filter.setStartDateFilterActive(false);
                        startDatePickerButton.setText("Başlangıç Tarihi");
                    }
                })
                ;

        final AlertDialog.Builder builderend = new AlertDialog.Builder(getActivity());
        builderend.setTitle("Bitiş Tarihi Seç")
                .setView(endDatePicker)
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filter.setEndDateFilterActive(true);
                        endDatePickerButton.setText("Başlangıç Tarihi: " +
                                endDatePicker.getYear() + "-" + (endDatePicker.getMonth() + 1)
                                + "-" + endDatePicker.getDayOfMonth());
                    }
                })
                .setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filter.setEndDateFilterActive(false);
                        endDatePickerButton.setText("Başlangıç Tarihi");
                    }
                });

        final AlertDialog dialogStart = builderstart.create();
        final AlertDialog dialogEnd = builderend.create();

        startDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStart.show();
            }
        });

        endDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEnd.show();
            }
        });

        //TODO change date to today's date
        startDatePicker.init(2015, 03, 12, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                filter.getDate().setStartYear(year);
                filter.getDate().setStartMonth(monthOfYear);
                filter.getDate().setStartDay(dayOfMonth);
            }
        });

        endDatePicker.init(2015, 03, 12, new DatePicker.OnDateChangedListener() {
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
                ((MainActivity)getActivity()).applyFilter(filter);
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
            tag_name.setChecked(true);
            filter.getTags().add(t);
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
