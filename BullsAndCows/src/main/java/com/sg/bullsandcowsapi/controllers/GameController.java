package com.sg.bullsandcowsapi.controllers;

import com.sg.bullsandcowsapi.dao.GameDao;
import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;
import com.sg.bullsandcowsapi.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bullsandcows")
public class GameController {

    private final GameDao dao;
    private final Service service;

    public GameController(GameDao dao, Service service) {
        this.dao = dao;
        this.service = service;
    }

    @GetMapping("/game")
    public List<Game> all() {
        return dao.getAll();
    }

    @GetMapping("/rounds/{gameID}")
    public List<Round> allByID(@PathVariable int gameID) {
        return service.getAllByGameID(gameID);
    }

    //starts a game and generates a 4 digit number, sets correct status, and returns 201 created
    //in controller w/ the id
    @PostMapping("/game")
    @ResponseStatus(value=HttpStatus.CREATED, reason="TESTING")
    public Game create(@RequestBody Game game) {
        return dao.add(game);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game result = dao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game initializeGame(Game game) {
        //make sure to return the created w/ id
        return service.initializeGame(game);
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeAGuess(@RequestBody Round round) {

        round.setPlayerGuess(round.getPlayerGuess());
        round.setGameID(round.getGameID());
        round.setGame(service.findGameByID(round.getGameID()));

        //set result
        return service.guess(round);

    }

}
