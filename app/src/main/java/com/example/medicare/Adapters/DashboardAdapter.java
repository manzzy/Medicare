package com.example.medicare.Adapters;

import android.content.Context;
import android.content.Intent;
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

import com.example.medicare.Models.DashboardModel;
import com.example.medicare.Fragments.AlarmFragment;
import com.example.medicare.Fragments.DashboardFragment;
import com.example.medicare.Fragments.DiseaseFragment;
import com.example.medicare.R;

import java.util.List;

public class DashboardAdapter  extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private Context context;
    private List<DashboardModel> mdata;

    public DashboardAdapter(Context context, List<DashboardModel> mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dash,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        DashboardModel dm= mdata.get(position);

        holder.title.setText(dm.getDashTitle());
        holder.description.setText(dm.getDashDescription());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.title.getText().toString()){
                    case "Home" :
                        setFragment(new DashboardFragment());
                        return;
                    case "Health Tips" :

                        setFragment(new DiseaseFragment());
                        return;
                    case "Medicine Alarm" :
                        setFragment(new AlarmFragment());
                        return;
                    case "Share" :
                        Intent intentInvite = new Intent(Intent.ACTION_SEND);
                        intentInvite.setType("text/plain");
                        String body = "APP Link";
                        String subject =" Share Medicare to your loved ones ";
                        intentInvite.putExtra(Intent.EXTRA_SUBJECT,subject);
                        intentInvite.putExtra(Intent.EXTRA_TEXT,body);
                        v.getContext().startActivity(Intent.createChooser(intentInvite,"Share Using"));
                        return;
                    default:
                        return;

                }
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
        TextView title, description;
        ImageView dashImg;
        ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.dash_title);
            description = itemView.findViewById(R.id.dash_description);
            dashImg = itemView.findViewById(R.id.dash_img);
            parent = itemView.findViewById(R.id.dash_parent);
        }
    }
}
