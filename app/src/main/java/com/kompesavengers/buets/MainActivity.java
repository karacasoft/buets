package com.kompesavengers.buets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kompesavengers.buets.api.EventsRequest;
import com.kompesavengers.buets.api.Request;
import com.kompesavengers.buets.api.TagsRequest;
import com.kompesavengers.buets.fragments.AkisFragment;
import com.kompesavengers.buets.fragments.FilterFragment;
import com.kompesavengers.buets.fragments.SingleEventFragment;
import com.kompesavengers.buets.model.DateFilter;
import com.kompesavengers.buets.model.Event;
import com.kompesavengers.buets.model.Filter;
import com.kompesavengers.buets.model.Tag;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        OnMapReadyCallback,
        AkisFragment.OnEventClickListener
    {

    private ArrayList<Event> events;
    private ArrayList<Tag> tags = new ArrayList<>();
    private View spinner;

    public void applyFilter(Filter filter)
    {
        ArrayList<Event> filteredEvents = filterEvents(filter);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(mapFragment == null)
            mapFragment = new SupportMapFragment();

        mapFragment.getMap().clear();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mapFragment)
                .commit();

        setMarkers(mapFragment.getMap(), filteredEvents);
    }

    public ArrayList<Event> filterEvents(Filter filter)
    {
        ArrayList<Event> filteredEvents = new ArrayList<>();

        if(filter.isStartDateFilterActive())
        {
            if(filter.isEndDateFilterActive())
            {
                for(Event e : events) {
                    if (isBetween(filter.getDateFilter(), DateFilter.parseFromStrings(e.getStartDate(), e.getEndDate()))) {
                        for (Tag t : filter.getTags()) {
                            if (e.getTags().contains(t.getId())) {
                                filteredEvents.add(e);
                                break;
                            }
                        }
                    }
                }
            }else{
                for(Event e : events) {
                    if (isBetween(filter.getDateFilter(), DateFilter.parseFromStrings(e.getStartDate(), e.getStartDate()))) {
                        for (Tag t : filter.getTags()) {
                            if (e.getTags().contains(t.getId())) {
                                filteredEvents.add(e);
                                break;
                            }
                        }
                    }
                }
            }
        }else{
            if(filter.isEndDateFilterActive())
            {
                for(Event e : events) {
                    if (isBetween(filter.getDateFilter(), DateFilter.parseFromStrings(e.getEndDate(), e.getEndDate()))) {
                        for (Tag t : filter.getTags()) {
                            if (e.getTags().contains(t.getId())) {
                                filteredEvents.add(e);
                                break;
                            }
                        }
                    }
                }
            }else{
                for(Event e : events) {
                    for (Tag t : filter.getTags()) {
                        if (e.getTags().contains(t.getId())) {
                            filteredEvents.add(e);
                            break;
                        }
                    }
                }
            }
        }



        return filteredEvents;
    }

    public boolean isBetween(DateFilter filter,DateFilter dateFilter)
    {

        Calendar c1 = Calendar.getInstance();
        c1.set(filter.getStartYear(), filter.getStartMonth(), filter.getStartDay());

        Calendar c2 = Calendar.getInstance();
        c2.set(filter.getEndYear(), filter.getEndMonth(), filter.getEndDay());

        Calendar c3 = Calendar.getInstance();
        c3.set(dateFilter.getStartYear(), dateFilter.getStartMonth() - 1, dateFilter.getStartDay());

        Calendar c4 = Calendar.getInstance();
        c4.set(dateFilter.getEndYear(), dateFilter.getEndMonth() - 1, dateFilter.getEndDay());

        if((c3.after(c1) && c3.before(c2)) || (c4.after(c1) && c4.before(c2)))
        {
            return true;
        }
        return false;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setMarkers(GoogleMap googleMap, ArrayList<Event> events)
    {
        for (Event e : events)
        {
             googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(e.getPlace().getCoordLat(), e.getPlace().getCoordLong()), 13));

             googleMap.addMarker(new MarkerOptions()
                    .title(e.getName())
                    .snippet(e.getStartDate())
                    .position(new LatLng(e.getPlace().getCoordLat(),e.getPlace().getCoordLong())));
        }
    }

    @Override
    public void onEventClick(Event e) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, SingleEventFragment.newInstance(e))
                .addToBackStack("list")
                .commit();
    }

    private void startEventsRequest()
    {
        EventsRequest eventsRequest = (EventsRequest) new EventsRequest()
                .setCallback(new Request.RequestCallback() {
                    @Override
                    public void onRequest() {
                        spinnerVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResult(int errorCode, final String errorString, ArrayList array) {
                        if(errorCode == 0) {
                            events.clear();
                            events.addAll(array);
                            startTagsRequest();
                        } else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    spinnerVisibility(View.GONE);
                                    showError("Hata", errorString);
                                }
                            });
                        }
                    }
                });
        eventsRequest.execute();
    }

    public void startTagsRequest()
    {
        TagsRequest tagsRequest = (TagsRequest) new TagsRequest()
                .setCallback(new Request.RequestCallback() {
                    @Override
                    public void onRequest() {}

                    @Override
                    public void onResult(int errorCode, final String errorString, ArrayList array) {
                        if(errorCode == 0)
                        {
                            tags.clear();
                            tags.addAll(array);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    spinnerVisibility(View.GONE);
                                    mapFragment.getMapAsync(MainActivity.this);
                                }
                            });

                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    spinnerVisibility(View.GONE);
                                    showError("Hata", errorString);
                                }
                            });
                        }
                    }
                });
        tagsRequest.execute();
    }

    public void spinnerVisibility(int visibility)
    {
        if(spinner == null)
            spinner = findViewById(R.id.loadingLayout);

        spinner.setVisibility(visibility);
    }

    public void showError(String title, String detail)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(detail)
                .setTitle(title)
                .setNeutralButton("OK.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private SupportMapFragment mapFragment;
    private ImageView filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.main_theme_color)));

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        filterButton = (ImageView) findViewById(R.id.filter_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterView();
            }
        });

    }

    private void showFilterView()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FilterFragment.newInstance())
                .addToBackStack("filters")
                .commit();
    }

    public void setFilterButtonVisibility(int visibility)
    {
        filterButton.setVisibility(visibility);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(mapFragment == null){
            mapFragment = new SupportMapFragment();
        }

        switch (position)
        {
            case 0:
                events = new ArrayList<Event>();
                startEventsRequest();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mapFragment)
                        .commit();
                break;
            case 1:
                spinnerVisibility(View.VISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AkisFragment.newInstance())
                        .commit();
                break;
            default:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
        }
        restoreActionBar();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMyLocationEnabled(true);
        setMarkers(googleMap, events);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
