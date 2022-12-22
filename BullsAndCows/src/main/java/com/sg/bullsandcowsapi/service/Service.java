package com.sg.bullsandcowsapi.service;

import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;

import java.util.List;

public interface Service {
    Game initializeGame(Game newGame);
    Round guess(Round round);
    List<Round> getAllByGameID(int gameID);
    List<Game> getAll();
    Game findGameByID(int id);

}
