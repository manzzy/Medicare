package com.example.medicare.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medicare.Fragments.AlarmFragment;

import java.util.List;

public class DataViewModel extends AndroidViewModel{
    private DatabaseRepository mDataRepository;
    private LiveData<List<AlarmModel>> mListLiveData;
    AlarmFragment alarmFragment;

    public DataViewModel(@NonNull Application application) {
        super(application);
        mDataRepository = new DatabaseRepository((application));
        mListLiveData = mDataRepository.getAllData();
        alarmFragment = new AlarmFragment();
    }

    public void insertItem(AlarmModel alarmModel) {
        mDataRepository.insert(alarmModel);
    }

    public void deleteAll() {
        mDataRepository.deleteAll();
    }

    public void deleteItem(AlarmModel alarmModel) {
        mDataRepository.delete(alarmModel);
    }

    public void deleteItemById(int idItem) {
        mDataRepository.deleteItemById(idItem);
    }

    public LiveData<List<AlarmModel>> getAllData() {
        return mListLiveData;
    }

    public AlarmModel getDataById(int itemId) {
        return mDataRepository.getItemById(itemId);
    }
}
