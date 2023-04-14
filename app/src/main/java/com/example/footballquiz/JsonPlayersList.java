package com.example.footballquiz;

import com.example.footballquiz.playersRoom.PlayersEntity;

import java.util.List;

public class JsonPlayersList {
    private int version;
    private List<PlayersEntity> players;

    public int getVersion(){
        return version;
    }

    public void setVersion(int version){
        this.version = version;
    }

    public List<PlayersEntity> getPlayers(){
        return players;
    }

    public void setPlayers(List<PlayersEntity> playersEntities){
        this.players = playersEntities;
    }
}
