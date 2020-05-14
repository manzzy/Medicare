package com.example.medicare.room;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class GetDataAsynchTask extends AsyncTask<Integer,Void, AlarmModel> {
    private static WeakReference<ItemDelegate> itemDelegateWeakReference;
    DataViewModel dataViewModel;

    public GetDataAsynchTask(ItemDelegate itemDelegate,DataViewModel dataViewModel) {
        itemDelegateWeakReference=new WeakReference<>(itemDelegate);
        this.dataViewModel=dataViewModel;
    }

    @Override
    protected AlarmModel doInBackground(Integer... integers) {
        return dataViewModel.getDataById(integers[0]);
    }

    @Override
    protected void onPostExecute(AlarmModel alarmModel) {
        super.onPostExecute(alarmModel);
        itemDelegateWeakReference.get().onItemRetrieved(alarmModel);
    }
}
