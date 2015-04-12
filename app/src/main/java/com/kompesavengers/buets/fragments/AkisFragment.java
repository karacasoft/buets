package com.kompesavengers.buets.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kompesavengers.buets.MainActivity;
import com.kompesavengers.buets.R;
import com.kompesavengers.buets.api.EventsRequest;
import com.kompesavengers.buets.api.Request;
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

    private OnEventClickListener onEventClickListener;

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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            this.onEventClickListener = (OnEventClickListener) activity;
        }catch (ClassCastException e)
        {
            Log.w("AkisFragment", "The caller Activity must implement" +
                    "OnEventClickListener interface.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onEventClickListener = null;
    }

    private void startFeedRequest()
    {
        //TODO: Dummy data will be used until web services are ready

        EventsRequest eventsRequest = (EventsRequest) new EventsRequest()
                .setCallback(new Request.RequestCallback() {
                    @Override
                    public void onRequest() {
                        //TODO show loading icon
                    }

                    @Override
                    public void onResult(int errorCode, final String errorString, ArrayList array) {
                        if(errorCode == 0) {
                            events.clear();
                            events.addAll(array);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    populateView();
                                }
                            });
                        }else{
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((MainActivity)getActivity()).showError("Hata", errorString);
                                }
                            });

                        }
                    }
                });
        eventsRequest.execute();


    }

    private void populateView()
    {
        for(final Event e : events)
        {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_akis, this.eventsLayout, false);

            TextView eventTitle = (TextView) v.findViewById(R.id.event_title);
            TextView eventDate = (TextView) v.findViewById(R.id.event_date);
            TextView eventDateEnd = (TextView) v.findViewById(R.id.event_date_end);
            TextView eventOrganizer = (TextView) v.findViewById(R.id.event_organizer);
            Button eventDetails = (Button) v.findViewById(R.id.event_details_button);

            eventTitle.setText(e.getName());
            eventDate.setText("Başlangıç Tarihi: " + e.getStartDate());
            eventDateEnd.setText("Bitiş Tarihi: " + e.getEndDate());
            eventOrganizer.setText(String.valueOf(e.getOrganizerId()));

            eventDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(AkisFragment.this.onEventClickListener != null)
                    {
                        AkisFragment.this.onEventClickListener.onEventClick(e);
                    }
                }
            });


            this.eventsLayout.addView(v);
        }
    }

    public interface OnEventClickListener
    {
        public void onEventClick(Event e);
    }


}
