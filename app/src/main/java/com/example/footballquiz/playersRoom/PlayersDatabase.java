package com.example.footballquiz.playersRoom;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {PlayersEntity.class}, version = 10)
public abstract class PlayersDatabase extends RoomDatabase{
    public abstract PlayersDao playersDao();
}
