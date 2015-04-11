package com.kompesavengers.buets.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kompesavengers.buets.R;
import com.kompesavengers.buets.model.Event;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleEventFragment extends Fragment {

    private Event event;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment SingleEventFragment.
     */
    public static SingleEventFragment newInstance(Event param1) {
        SingleEventFragment fragment = new SingleEventFragment();
        fragment.setEvent(param1);
        return fragment;
    }

    public SingleEventFragment() {
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
        View v = inflater.inflate(R.layout.fragment_single_event, container, false);
        TextView eventTitle = (TextView) v.findViewById(R.id.single_event_title);
        TextView eventStartDate = (TextView) v.findViewById(R.id.single_event_date);
        TextView eventEndDate = (TextView) v.findViewById(R.id.single_event_date_end);
        TextView eventOrganizer = (TextView) v.findViewById(R.id.single_event_organizer);
        TextView eventDesc = (TextView) v.findViewById(R.id.single_event_description);
        TextView eventURL = (TextView) v.findViewById(R.id.single_event_url);
        ImageView eventLogo = (ImageView) v.findViewById(R.id.single_event_logo);

        eventTitle.setText(event.getName());
        eventStartDate.setText("Başlangıç Tarihi: " + event.getStartDate());
        eventEndDate.setText("Bitiş Tarihi: " + event.getEndDate());
        eventOrganizer.setText(String.valueOf(event.getOrganizerId()));
        eventDesc.setText(event.getDetail());
        eventURL.setText(event.getUrl());

        //TODO get event logo

        return v;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
