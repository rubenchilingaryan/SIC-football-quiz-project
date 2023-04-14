package com.example.footballquiz.playersRoom;

import android.content.Context;

import androidx.room.Room;

public class PlayersDatabaseClient {
    public static final String DB_NAME = "playersData.db";
    public static PlayersDatabaseClient instance;
    public static PlayersDatabase playersDatabase;

    public PlayersDatabaseClient(Context context){
        playersDatabase = Room.databaseBuilder(context, PlayersDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
    }

    public static synchronized PlayersDatabaseClient getInstance(Context context){
        if (instance == null) {
        instance = new PlayersDatabaseClient(context);
    }
        return instance;
    }

    public PlayersDatabase getPlayersDatabase(){
        return playersDatabase;
    }
}
