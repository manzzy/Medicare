package com.example.medicare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Models.Disease;
import com.example.medicare.Fragments.DetailsFragment;
import com.example.medicare.Models.DiseaseModel;
import com.example.medicare.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class DiseaseListAdapter extends FirestoreRecyclerAdapter<DiseaseModel,DiseaseListAdapter.DiseaseListHolder> {
    private Context context;
    private  RecyclerViewClickInterface recyclerViewClickInterface;
    DetailsFragment  detailsFragment;

    public DiseaseListAdapter(@NonNull FirestoreRecyclerOptions<DiseaseModel> options, Context context) {
        super(options);
        this.context = context;
    }

    public DiseaseListAdapter(@NonNull FirestoreRecyclerOptions<DiseaseModel> options, Context context, RecyclerViewClickInterface recyclerViewClickInterface
    ) {
        super(options);
        this.context = context;
    this.recyclerViewClickInterface=recyclerViewClickInterface;
        detailsFragment=new DetailsFragment();

    }

    public DiseaseListAdapter(@NonNull FirestoreRecyclerOptions<DiseaseModel> options, Context context, DetailsFragment detailsFragment) {
        super(options);
        this.context = context;
        this.detailsFragment = detailsFragment;
    }

    public class  DiseaseListHolder extends RecyclerView.ViewHolder{
     TextView textViewItemDisease,textViewItemDiseaseDescription;
        ConstraintLayout parentLayout;
        TextView description;

     public DiseaseListHolder(@NonNull View itemView) {
         super(itemView);
         textViewItemDisease=itemView.findViewById(R.id.item_disease_name);
         textViewItemDiseaseDescription=itemView.findViewById(R.id.item_disease_short_description);
         parentLayout = itemView.findViewById(R.id.parentLayout);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                recyclerViewClickInterface.onItemClick(getAdapterPosition());
             }
         });
        

     }
 }

    @NonNull
    @Override
    public DiseaseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease,parent,false);
        return new DiseaseListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseListHolder holder, final int position, DiseaseModel model) {
      holder.textViewItemDisease.setText(model.getTitle());
        holder.textViewItemDiseaseDescription.setText(model.getDescription());
//i cant call a fragment from fragment



    }




}
