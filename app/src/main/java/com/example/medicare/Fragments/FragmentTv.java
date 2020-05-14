package com.example.medicare.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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


public class FragmentTv extends Fragment {
TextView whatIsTvTitle,whatIsTvDescription,tvTypeTitle,tvTypeDescription,causeTvTitle,causeTvDescription,tvSymptomTitle,tvSymptomDescription,
    tvComplicationTitle,tvComplicationDescription;
FirebaseFirestore firebaseFirestore;
View view;
    private ProgressBar progressBar;
     TextView retry;
public FragmentTv() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_tv, container, false);
         initializedView();
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

    public void initializedView(){
        whatIsTvTitle=(TextView) view.findViewById(R.id.what_is_Tb);
        whatIsTvDescription=(TextView)view.findViewById(R.id.what_is_tb_description);
        tvTypeTitle=(TextView)view.findViewById(R.id.type_Tb_title);
        tvTypeDescription=(TextView)view.findViewById(R.id.type_Tb_description);
        causeTvTitle=(TextView)view.findViewById(R.id.tb_cause_title);
        causeTvDescription=(TextView)view.findViewById(R.id.tb_cause_description);
        tvSymptomTitle=(TextView)view.findViewById(R.id.tb_symptom_title);
        tvSymptomDescription=(TextView)view.findViewById(R.id.tb_symptom_description);
        tvComplicationTitle=(TextView)view.findViewById(R.id.tb_complication_title);
        tvComplicationDescription=(TextView)view.findViewById(R.id.compilation_description);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar2);
        retry=(TextView)view.findViewById(R.id.retry);
    }
    public void fetchData(){
        progressBar.setVisibility(View.VISIBLE);
        Task<DocumentSnapshot> howToUseMaskDocument=firebaseFirestore.collection("Tuberculosis").document("tb").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                retry.setVisibility(View.INVISIBLE);

                whatIsTvTitle.setText(documentSnapshot.getString("title"));
                whatIsTvDescription.setText(documentSnapshot.getString("description"));
                tvTypeTitle.setText(documentSnapshot.getString("tbTypetitle"));
                tvTypeDescription.setText(documentSnapshot.getString("tbTypeDescription"));
                causeTvTitle.setText(documentSnapshot.getString("cause"));
                causeTvDescription.setText(documentSnapshot.getString("causeTitle"));
                tvSymptomTitle.setText(documentSnapshot.getString("tbSymptomTitle"));
                tvSymptomDescription.setText(documentSnapshot.getString("tbSymptomDescription").replaceAll("nl","\n"));
                tvComplicationTitle.setText(documentSnapshot.getString("complicationTitle"));
                tvComplicationDescription.setText(documentSnapshot.getString("complicationdecription").replaceAll("nl","\n"));

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
}
