package com.example.medicare.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import com.example.medicare.Adapters.DashboardAdapter;
import com.example.medicare.Models.DashboardModel;
import com.example.medicare.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private List<DashboardModel> mdata;
    private DashboardAdapter madapter;
    private RecyclerView rv;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mdata = new ArrayList<>();
        rv = view.findViewById(R.id.rv_dashboard);

        add();
        madapter = new DashboardAdapter(view.getContext(),mdata);
        rv.setAdapter(madapter);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void add() {
        mdata.add(new DashboardModel("Home","Here you can find some data of the app to naviage minamin.",1));
        mdata.add(new DashboardModel("Health Tips","find all the required information needed on a disease, these information includes treatments and descriptions of disease.",1));
        mdata.add(new DashboardModel("Medicine Alarm","Set alarms to remind you the time you have to take your medicine.",1));
        mdata.add(new DashboardModel("Share","Share the app to your friends and family, and help them stay healthy and informed.",1));

    }
}
