package com.sg.bullsandcowsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Round {
    private int id;
    private Timestamp timePlayed;
    private String playerGuess;
    private String result;
    private int gameID;
    @JsonIgnore
    private Game game;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Timestamp timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getPlayerGuess() {
        return playerGuess;
    }

    public void setPlayerGuess(String playerGuess) {
        this.playerGuess = playerGuess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
