package com.kompesavengers.buets.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kompesavengers.buets.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkisFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_akis, container, false);
    }




}
