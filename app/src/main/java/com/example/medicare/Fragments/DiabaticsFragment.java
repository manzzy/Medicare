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


public class DiabaticsFragment extends Fragment {
 TextView whatIsDiabaticsTitle,whatIsDiabaticsDescribtion,type1Title,type1Description,
         type1SymptomTitle,type1SymptomDescription,type1TreatmentTitle,type1TreatmentDescription,
         type2SymptomTitle,type2SymptomDescription,type2TreatmentTitle,type2TreatmentDescription,
    type2Title,type2Description,type2RiskfactorTitle,type2RiskfactorDescription;

FirebaseFirestore firebaseFirestore;
View view;
    private ProgressBar progressBar;
    TextView retry;


    public DiabaticsFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.fragment_diabatics, container, false);
        initializedView();
        firebaseFirestore=FirebaseFirestore.getInstance();
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
        progressBar=view.findViewById(R.id.progressBar2);
        retry=view.findViewById(R.id.retry);
                whatIsDiabaticsTitle=view.findViewById(R.id.what_is_diabatics_title);
                whatIsDiabaticsDescribtion=view.findViewById(R.id.what_is_diabatics);
                type1Title=view.findViewById(R.id.type1_title);
                type1Description=view.findViewById(R.id.type1_description);
                type1SymptomTitle=view.findViewById(R.id.symptoms_type1_title);
                type1SymptomDescription=view.findViewById(R.id.symptoms_type1_diabatics_description);
                type1TreatmentTitle=view.findViewById(R.id.type1_treatement_title);
                type1TreatmentDescription=view.findViewById(R.id.type1_treatement_title_description);
                type2Title=view.findViewById(R.id.type2_diabatics_title);
                type2Description=view.findViewById(R.id.type2_diabatics_title_description);
                type2RiskfactorTitle=view.findViewById(R.id.type2_riskfactor_title);
                type2RiskfactorDescription=view.findViewById(R.id.riskfactor_description);
                type2SymptomTitle=view.findViewById(R.id.type2_symptom_title);
                type2SymptomDescription=view.findViewById(R.id.type2_symptom_description);
                type2TreatmentTitle=view.findViewById(R.id.type2_treatment_title);
                type2TreatmentDescription=view.findViewById(R.id.type2_treatment_description);


            }
            public void fetchData(){
                progressBar.setVisibility(View.VISIBLE);
                Task<DocumentSnapshot> howToUseMaskDocument=firebaseFirestore.collection("Diabatics").document("diabatics").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        progressBar.setVisibility(View.INVISIBLE);
                        retry.setVisibility(View.INVISIBLE);
                        whatIsDiabaticsTitle.setText(documentSnapshot.getString("whatIsDiabaticsTitle"));
                        whatIsDiabaticsDescribtion.setText(documentSnapshot.getString("whatIsDiabaticsDescription"));
                        type1Title.setText(documentSnapshot.getString("type1DibaticsTitle"));
                        type1Description.setText(documentSnapshot.getString("type1DiabaticsDescription"));
                        type1SymptomTitle.setText(documentSnapshot.getString("type1DiabaticsSymptomTitle"));
                        type1SymptomDescription.setText(documentSnapshot.getString("type1DiabaticSymptom").replaceAll("nl","\n"));
                        type1TreatmentTitle.setText(documentSnapshot.getString("type1TreatmentTitle"));
                        type1TreatmentDescription.setText(documentSnapshot.getString("type1TreatmentDescription").replaceAll("nl","\n"));
                        type2Title.setText(documentSnapshot.getString("type2DiabaticsTitle"));
                        type2Description.setText(documentSnapshot.getString("type2DiabaticsDescription"));
                        type2RiskfactorTitle.setText(documentSnapshot.getString("riskFactorTitle"));
                        type2RiskfactorDescription.setText(documentSnapshot.getString("riskFactorDescription").replaceAll("nl","\n"));
                        type2SymptomTitle.setText(documentSnapshot.getString("type2DiabaticsSymptomTitle"));
                        type2SymptomDescription.setText(documentSnapshot.getString("type2DiabaticsSymptomDescription").replaceAll("nl","\n"));
                        type2TreatmentTitle.setText(documentSnapshot.getString("type2DiabaticsTreatmentTitle"));
                        type2TreatmentDescription.setText(documentSnapshot.getString("type2DiabaticsTreatmentDescription"));



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
