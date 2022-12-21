package com.sg.bullsandcowsapi.service;

import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;

import java.util.List;

public interface Service {

    //returns the game object associated with the new game, make sure to ensure the correct status, generate an answer, and return 201 in controller w/ new game id
    //add the rounds to the
    Game initializeGame(Game newGame);

    //makes a guess by passing the guess and gameID (as JSON)
    //helper methods? calc result - mark game accordingly - return the round with results of the round, if finished, just change the status
    Round guess(Round round);

     List<Round> getAllByGameID(int gameID);

    //get list of all games, do not include answer to in progress games
    List<Game> getAll();
    //return a specific game based on id, do not include answer to in progress games
    Game findGameByID(int id);

}
