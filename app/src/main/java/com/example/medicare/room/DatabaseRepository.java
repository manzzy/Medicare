package com.example.medicare.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseRepository {
    private DataDAO mDataDao;
    private LiveData<List<AlarmModel>> mAllData;


    public DatabaseRepository(Application application) {
        DatabaseRoom databaseRoom=DatabaseRoom.getDatabase(application);
        this.mDataDao=databaseRoom.dataDAO();
        this.mAllData=mDataDao.getAllData();
    }
    LiveData<List<AlarmModel>> getAllData(){
        return mAllData;
    }
    public AlarmModel getItemById(int idItem){
        return mDataDao.getDataById(idItem);
    }
    public void insert(AlarmModel item) {
        new insertAsyncTask(mDataDao).execute(item);
    }
    public void deleteAll() {
        new deleteALlAsyncTask(mDataDao).execute();
    }
    public void delete(AlarmModel item) {
        new deleteAsyncTask(mDataDao).execute(item);
    }
    public void deleteItemById(int idItem){
        new deleteByIDAsyncTask(mDataDao).execute(idItem);
    }

    private static class insertAsyncTask extends AsyncTask<AlarmModel,Void,Void> {
        private DataDAO mAsyncTaskDao;

        insertAsyncTask(DataDAO dataDAO) {
            mAsyncTaskDao = dataDAO;
        }

        @Override
        protected Void doInBackground(AlarmModel... alarmModels) {
            mAsyncTaskDao.insertItem(alarmModels[0]);
            return null;
        }
    }
    private static class deleteALlAsyncTask extends AsyncTask<Void,Void,Void> {
        private DataDAO mAsyncTaskDao;

        deleteALlAsyncTask(DataDAO dataDAO) {
            mAsyncTaskDao = dataDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<AlarmModel,Void,Void>{
        private DataDAO mAsyncTaskDao;
        deleteAsyncTask(DataDAO dataDAO){
            mAsyncTaskDao=dataDAO;
        }
        @Override
        protected Void doInBackground(AlarmModel... alarmModels) {
            mAsyncTaskDao.deleteItem(alarmModels[0]);
            return null;
        }
    }
    private static class deleteByIDAsyncTask extends AsyncTask<Integer,Void,Void>{
        private DataDAO mAsyncTaskDao;
        deleteByIDAsyncTask(DataDAO dataDAO){
            mAsyncTaskDao=dataDAO;
        }
        @Override
        protected Void doInBackground(Integer... longs) {
            mAsyncTaskDao.deleteByItemId(longs[0]);
            return null;
        }
    }
}
