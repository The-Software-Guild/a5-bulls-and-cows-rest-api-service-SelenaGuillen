package com.sg.bullsandcowsapi.service;

import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;

import java.util.List;

public interface Service {
    /*
    * "begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
"guess" – POST – Makes a guess by passing the guess and gameId in as JSON.
* The program must calculate the results of the guess and mark the game finished
* if the guess is correct. It returns the Round object with the results filled in.
"game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
*
* You must include a Service layer to manage the game rules, such as generating initial answers for a game and calculating the results of a guess.
    * */

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

    //returns a list of rounds for the game (helper method) sorted by time
    //get rounds by game ID from round dao
    List<Round> getById(int gameID);
}
