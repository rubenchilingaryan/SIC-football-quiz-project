package com.example.footballquiz.playersRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayersDao {
    @Query("SELECT * FROM players")
    List<PlayersEntity> getAll();

    @Query("SELECT * FROM players WHERE id = :playerId")
    PlayersEntity getPlayerById(int playerId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertPlayer(PlayersEntity playersEntity);

    @Update
    int updatePlayer(PlayersEntity playersEntity);

    @Delete
    int deletePlayer(PlayersEntity playersEntity);

    @Query("DELETE FROM players")
    int deleteAllPlayers();
}
