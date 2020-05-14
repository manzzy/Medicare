package com.example.medicare.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class DetailsFragment extends Fragment {
    TextView typhoidTitle,typhoidDescription,typhoidCauseTitle,typhoidCause,typhoidSymptomsTitle,typhoidSymptoms,
           typhoidTreatmentTitle,typhoidTreatment ,howToAvoidTyphoid,retry;
    FirebaseFirestore   firebaseFirestore;
ProgressBar progressBar;
View view;

    public DetailsFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details, container, false);
        intializingView();
        firebaseFirestore= FirebaseFirestore.getInstance();
       fetchData();
       retry.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               retry.setVisibility(v.INVISIBLE);
               fetchData();
           }
       });

         return view;
    }


    public void intializingView(){
        typhoidTitle=view.findViewById(R.id.what_is);
        typhoidDescription=view.findViewById(R.id.what);
        typhoidSymptomsTitle=view.findViewById(R.id.symptoms);
        typhoidSymptoms=view.findViewById(R.id.disease_symptoms);
        typhoidCauseTitle=view.findViewById(R.id.causes);
        typhoidCause=view.findViewById(R.id.disease_cause);
        typhoidTreatmentTitle=view.findViewById(R.id.treatment);
        howToAvoidTyphoid=view.findViewById(R.id.disease_treatment);
        progressBar=view.findViewById(R.id.progressBar2);
        retry=(TextView)view.findViewById(R.id.retry);

    }
    public void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> howToUseMaskDocument=firebaseFirestore.collection("Typhoid").document("typhoid").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);
                typhoidTitle.setText(documentSnapshot.getString("WhatIsTyphoid"));
                typhoidDescription.setText(documentSnapshot.getString("WhatIsTyphoidTitle"));
                typhoidSymptomsTitle.setText(documentSnapshot.getString("SymptomTitle"));
                typhoidSymptoms.setText(documentSnapshot.getString("Symptom").replaceAll("nl","\n"));
                typhoidCauseTitle.setText(documentSnapshot.getString("CauseTitle"));
                typhoidCause.setText(documentSnapshot.getString("Cause"));
                typhoidTreatmentTitle.setText(documentSnapshot.getString("HowToAvoidTitle"));
                howToAvoidTyphoid.setText(documentSnapshot.getString("HowToAvoid").replaceAll("nl","\n")
                );

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.VISIBLE);
                Toast.makeText(requireContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
