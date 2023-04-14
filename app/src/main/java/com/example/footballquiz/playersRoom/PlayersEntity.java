package com.example.footballquiz.playersRoom;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "players")
public class PlayersEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "price")
    public Integer price;

    @ColumnInfo(name = "goals")
    public Integer goals;

    @ColumnInfo(name = "assists")
    public Integer assists;

    @ColumnInfo(name = "imagePath")
    public String imagePath = "";

    public PlayersEntity(long id, String name, Integer price, Integer goals, Integer assists, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.goals = goals;
        this.assists = assists;
        this.imagePath = imagePath;
    }
}
