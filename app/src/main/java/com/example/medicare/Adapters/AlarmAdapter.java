package com.example.medicare.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.Fragments.AlarmFragment;
import com.example.medicare.room.AlarmModel;
import com.example.medicare.R;

import java.util.Calendar;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private Context context;
    public List<AlarmModel> mdata;
    AlarmFragment alarmFragment;

    public AlarmAdapter(Context context, List<AlarmModel> mdata) {
        this.context = context;
        this.mdata = mdata;
        alarmFragment=new AlarmFragment();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alarm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.time.setText(mdata.get(position).getCalendar().get(Calendar.HOUR_OF_DAY) + " : "+ mdata.get(position).getCalendar().get(Calendar.MINUTE));
        holder.name.setText(mdata.get(position).getName());
        final SharedPreferences sharedPreferences=context.getSharedPreferences("toggle",Context.MODE_PRIVATE);
        Boolean toggleChecked=sharedPreferences.getBoolean("toggle"+position,true);
        if(toggleChecked){
            holder.toggleButton.setChecked(true);
        }
        else {
            holder.toggleButton.setChecked(false);
        }
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("toggle"+position,true);
                    editor.commit();
                    alarmFragment.restartAlarm(position);
                }
                else {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("toggle"+position,false);
                    editor.commit();
                    alarmFragment.cancelAlarm(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,name;
        ToggleButton toggleButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.alarm_time);
            name  = itemView.findViewById(R.id.alarm_name);
            toggleButton=itemView.findViewById(R.id.switch2);
        }
    }
    public void setListData(List<AlarmModel> alarmModels){
        mdata=alarmModels;
    }
}
