package com.example.medicare.Fragments;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.medicare.Adapters.AlarmAdapter;
import com.example.medicare.room.AlarmModel;
import com.example.medicare.Alarm.AlarmReciever;
import com.example.medicare.room.DataViewModel;
import com.example.medicare.room.GetDataAsynchTask;
import com.example.medicare.room.ItemDelegate;
import com.example.medicare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class AlarmFragment extends Fragment implements ItemDelegate {
    Dialog mDialog;
    String name;
    int hour, minute;
    AlarmManager alarmManager;
    AlarmAdapter adapter;
    List<AlarmModel> mdata;
    RecyclerView rv;
    FloatingActionButton fab;
    private static DataViewModel dataViewModel;
    public AlarmModel alarmModel;

    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fab = view.findViewById(R.id.fab_add);
        mDialog = new Dialog(view.getContext());
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_alarm);
        mdata = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        adapter = new AlarmAdapter(view.getContext(), mdata);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    private void showPopup() {
        final EditText medicineName;
        final Button mFinishBtn;
        final TimePicker timePicker;
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        mDialog.setContentView(R.layout.alarm_input_dialog);
        medicineName = mDialog.findViewById(R.id.alarm_name);
        mFinishBtn = mDialog.findViewById(R.id.done_btn);
        timePicker = mDialog.findViewById(R.id.timePicker);
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String medicine = medicineName.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                Log.d("TAG", "onClick: "+timePicker.getCurrentHour());
                Log.d("TAG", "onClick: "+timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                Intent intent = new Intent(getActivity(), AlarmReciever.class);
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("RSA",Context.MODE_PRIVATE);
                int rsa = sharedPreferences.getInt("rsa",0);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), ++rsa, intent, 0);
                alarmManager.setExact(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
                dataViewModel.insertItem(new AlarmModel(calendar, medicine,rsa));
                adapter.notifyDataSetChanged();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("rsa",rsa);
                editor.commit();
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }
    private void setFragment(Fragment fragment, Context context) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

//    @Override

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<AlarmModel>>() {
            @Override
            public void onChanged(List<AlarmModel> alarmModels) {
                if (alarmModels != null) {
                    adapter.setListData(alarmModels);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void cancelAlarm(int position){
        new GetDataAsynchTask(this,dataViewModel).execute(++position);
        if(alarmModel!=null){
            int rsa=alarmModel.getRsa();
            Intent intent =new Intent(getActivity().getBaseContext(),AlarmReciever.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(getContext(),rsa,intent,0);
            alarmManager=(AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void restartAlarm(int position){
        new GetDataAsynchTask(this,dataViewModel).execute(++position);
        if(alarmModel!=null){
            int rsa=alarmModel.getRsa();
            Calendar calendar=alarmModel.getCalendar();
            Intent intent =new Intent(getActivity().getBaseContext(),AlarmReciever.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(getContext(),rsa,intent,0);
            alarmManager=(AlarmManager) Objects.requireNonNull(getActivity()).getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
        }
    }
    @Override
    public void onItemRetrieved(AlarmModel alarmModel) {
        this.alarmModel=alarmModel;
    }
}