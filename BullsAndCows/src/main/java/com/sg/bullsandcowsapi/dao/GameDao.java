package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Game;

import java.util.List;

public interface GameDao {
    //use when starting a new game
    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);


}
