package com.sg.bullsandcowsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Round round = (Round) o;

        if (id != round.id) return false;
        if (gameID != round.gameID) return false;
        if (!Objects.equals(timePlayed, round.timePlayed)) return false;
        if (!Objects.equals(playerGuess, round.playerGuess)) return false;
        if (!Objects.equals(result, round.result)) return false;
        return Objects.equals(game, round.game);
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (timePlayed != null ? timePlayed.hashCode() : 0);
        result1 = 31 * result1 + (playerGuess != null ? playerGuess.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + gameID;
        result1 = 31 * result1 + (game != null ? game.hashCode() : 0);
        return result1;
    }
}
