package com.sg.bullsandcowsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Game {
    private int id;
    private String status;

    @JsonIgnore
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
