package com.example.medicare.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicare.Adapters.RecyclerViewClickInterface;
import com.example.medicare.Models.Disease;
import com.example.medicare.Adapters.DiseaseListAdapter;
import com.example.medicare.Models.DiseaseModel;
import com.example.medicare.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiseaseFragment extends Fragment implements RecyclerViewClickInterface {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    DiseaseListAdapter mAdapter;
    DetailsFragment detailsFragment = new DetailsFragment();
    FragmentTv fragmentTv=new FragmentTv();
    DiabaticsFragment diabaticsFragment=new DiabaticsFragment();

    public DiseaseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disease, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_disease);
        Query query = firestore.collection("Home");
        FirestoreRecyclerOptions<DiseaseModel> options = new FirestoreRecyclerOptions.Builder<DiseaseModel>()
                .setQuery(query, DiseaseModel.class)
                .build();
        mAdapter = new DiseaseListAdapter(options, view.getContext(), this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0) {
            setFragment(fragmentTv);

        } else if (position == 1) {
            setFragment(diabaticsFragment);
        } else if (position == 2) {
            setFragment(detailsFragment);
        }
    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
