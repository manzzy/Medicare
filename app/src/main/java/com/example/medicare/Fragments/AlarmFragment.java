package com.example.medicare.Fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.medicare.Adapters.AlarmAdapter;
import com.example.medicare.Models.AlarmModel;
import com.example.medicare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {
    Dialog mDialog;
    String name;
    int hour, minute;

    AlarmAdapter adapter;
    List<AlarmModel> mdata;
    RecyclerView rv;
    FloatingActionButton fab;
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
        super.onViewCreated(view, savedInstanceState);

        fab = view.findViewById(R.id.fab_add);
        mDialog = new Dialog(view.getContext());
        rv = view.findViewById(R.id.rv_alarm);
        mdata = new ArrayList<>();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        adapter = new AlarmAdapter(view.getContext(),mdata);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adapter);

    }

    private void showPopup() {
        final EditText medicineName;
        final Button mFinishBtn;
        mDialog.setContentView(R.layout.alarm_input_dialog);
        medicineName = mDialog.findViewById(R.id.alarm_name);
        mFinishBtn= mDialog.findViewById(R.id.set_alarmbtn);
        mFinishBtn.setEnabled(false);
        if(medicineName != null){
            mFinishBtn.setEnabled(true);
        }
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = medicineName.getText().toString();
                time();
                mDialog.dismiss();

            }
        });
        mDialog.show();
    }

    private void time() {
        DialogFragment df= new TimePickerFragment();
        df.show(getActivity().getSupportFragmentManager(),"Time Picker");


    }

    private void setFragment(Fragment fragment, Context context) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour = hourOfDay;
        this.minute = minute;
        addToRecycler();
    }

    private void addToRecycler() {
        mdata.add(new AlarmModel(hour,minute,name));

    }
}
