package com.example.footballquiz.viewModels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.footballquiz.playersRoom.PlayersDao;
import com.example.footballquiz.playersRoom.PlayersDatabase;
import com.example.footballquiz.playersRoom.PlayersDatabaseClient;
import com.example.footballquiz.playersRoom.PlayersEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends AndroidViewModel {

    private MutableLiveData<List<PlayersEntity>> players;
    private PlayersDatabase playersDatabase;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        players = new MutableLiveData<>();
        playersDatabase = PlayersDatabaseClient.getInstance(getApplication()).getPlayersDatabase();
    }

    public LiveData<List<PlayersEntity>> getPlayers(){
        return players;
    }

    public void addPlayer(PlayersEntity playersEntity){
        AsyncTask.execute(() ->{
            playersDatabase.playersDao().insertPlayer(playersEntity);
            List<PlayersEntity> currentList = playersDatabase.playersDao().getAll();
            players.postValue(currentList);
        });
    }

    public void addPlayers(ArrayList<PlayersEntity> playersEntities){
        AsyncTask.execute(() -> {
            for(PlayersEntity items : playersEntities){
                playersDatabase.playersDao().insertPlayer(items);
            }
            List<PlayersEntity> currentList = playersDatabase.playersDao().getAll();
            players.postValue(currentList);
        });
    }

    public void deleteAllPlayers(){
        AsyncTask.execute(() -> {
        playersDatabase.playersDao().deleteAllPlayers();
        List<PlayersEntity> currentList = playersDatabase.playersDao().getAll();
        players.postValue(currentList);
        });
    }

    public void deletePlayer(PlayersEntity playersEntity){
        AsyncTask.execute(() -> {
            playersDatabase.playersDao().deletePlayer(playersEntity);
            List<PlayersEntity> currentList = playersDatabase.playersDao().getAll();
            players.postValue(currentList);
        });
    }

    public void readPlayers(){
        AsyncTask.execute(() -> {
            List<PlayersEntity> currentList = playersDatabase.playersDao().getAll();
            players.postValue(currentList);
        });
    }
}
