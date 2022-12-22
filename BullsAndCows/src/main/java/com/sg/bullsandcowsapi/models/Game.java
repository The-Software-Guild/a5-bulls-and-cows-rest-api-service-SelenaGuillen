package com.sg.bullsandcowsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Game {

    private int id;

    private String status;

    @JsonIgnore
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (!Objects.equals(status, game.status)) return false;
        return Objects.equals(answer, game.answer);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
