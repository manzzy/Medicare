package com.example.medicare.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AlarmModel.class}, version=1,exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {
    private static DatabaseRoom databaseRoom;
    public abstract DataDAO dataDAO();

    public static DatabaseRoom getDatabase(Context context){
        if (databaseRoom == null) {
            databaseRoom= Room.databaseBuilder(context.getApplicationContext(),DatabaseRoom.class,DatabaseRoom.class.getName())
                    .build();
        }
        return databaseRoom;
    }
    public static void destroyDatabase(){
        databaseRoom=null;
    }
}