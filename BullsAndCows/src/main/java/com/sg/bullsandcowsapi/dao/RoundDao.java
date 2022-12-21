package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Round;

import java.util.List;

public interface RoundDao {
    Round add(Round round);

    List<Round> getAll();

    List<Round> getAllByGameID(int gameID);

}
