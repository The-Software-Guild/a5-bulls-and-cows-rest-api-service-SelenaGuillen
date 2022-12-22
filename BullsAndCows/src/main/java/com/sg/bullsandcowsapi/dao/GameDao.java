package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Game;

import java.util.List;

public interface GameDao {
    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);

    void updateGame(Game game);

    void deleteGameById(int id);
}
