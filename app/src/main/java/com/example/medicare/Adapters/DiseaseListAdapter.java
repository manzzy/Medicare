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
import com.example.medicare.R;

import java.util.List;

public class DiseaseListAdapter extends RecyclerView.Adapter<DiseaseListAdapter.ViewHolder> {
    private Context context;
    private List<Disease> mdata;
    DetailsFragment detailsFragment;

    public DiseaseListAdapter(Context context, List<Disease> mdata) {
        this.context = context;
        this.mdata = mdata;
        detailsFragment = new DetailsFragment();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_disease,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Disease disease = mdata.get(position);
        // image will be replaced here with glide
        holder.diseaseName.setText(disease.getName());
        holder.diseaseShortDescription.setText(disease.getShortDescription());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(detailsFragment);

            }
        });


    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView diseaseImg;
        TextView diseaseName, diseaseShortDescription;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diseaseImg = itemView.findViewById(R.id.item_disease_img);
            diseaseName = itemView.findViewById(R.id.item_disease_name);
            diseaseShortDescription = itemView.findViewById(R.id.item_disease_short_description);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }
    }
}
