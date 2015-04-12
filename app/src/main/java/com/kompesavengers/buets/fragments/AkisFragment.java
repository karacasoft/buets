package com.kompesavengers.buets.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kompesavengers.buets.R;
import com.kompesavengers.buets.model.Event;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkisFragment extends Fragment {

    private ArrayList<Event> events = new ArrayList<>();
    private LinearLayout eventsLayout;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AkisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkisFragment newInstance() {
        AkisFragment fragment = new AkisFragment();
        return fragment;
    }

    public AkisFragment() {
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_akis, container, false);

        eventsLayout = (LinearLayout) v.findViewById(R.id.eventsListLayout);
        startFeedRequest();

        return v;
    }

    private void startFeedRequest()
    {
        //TODO: Dummy data will be used until web services are ready

        Event e = new Event();
        e.setId(0);
        e.setName("Event1");
        e.setDetail("Detail1");
        e.setLogo_link("logo_link");
        e.setStartDate("12.06.2015");
        e.setEndDate("12.06.2015");
        e.setOrganizerId(0);

        Event e1 = new Event();
        e1.setId(1);
        e1.setName("Event2");
        e1.setDetail("Detail2");
        e1.setLogo_link("logo_link");
        e1.setStartDate("12.06.2015");
        e1.setEndDate("12.06.2015");
        e1.setOrganizerId(0);

        Event e2 = new Event();
        e2.setId(2);
        e2.setName("Event3");
        e2.setDetail("Detail3");
        e2.setLogo_link("logo_link");
        e2.setStartDate("12.06.2015");
        e2.setEndDate("12.06.2015");
        e2.setOrganizerId(0);

        Event e3 = new Event();
        e3.setId(3);
        e3.setName("Event4");
        e3.setDetail("Detail4");
        e3.setLogo_link("logo_link");
        e3.setStartDate("12.06.2015");
        e3.setEndDate("12.06.2015");
        e3.setOrganizerId(0);


        events.add(e);
        events.add(e1);
        events.add(e2);
        events.add(e3);

        populateView();

    }

    private void populateView()
    {
        for(Event e : events)
        {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_akis, this.eventsLayout, false);

            TextView eventTitle = (TextView) v.findViewById(R.id.event_title);
            TextView eventDate = (TextView) v.findViewById(R.id.event_date);
            TextView eventDateEnd = (TextView) v.findViewById(R.id.event_date_end);
            TextView eventOrganizer = (TextView) v.findViewById(R.id.event_organizer);

            eventTitle.setText(e.getName());
            eventDate.setText("Başlangıç Tarihi: " + e.getStartDate());
            eventDateEnd.setText("Bitiş Tarihi: " + e.getEndDate());
            eventOrganizer.setText(String.valueOf(e.getOrganizerId()));

            this.eventsLayout.addView(v);
        }
    }


}
