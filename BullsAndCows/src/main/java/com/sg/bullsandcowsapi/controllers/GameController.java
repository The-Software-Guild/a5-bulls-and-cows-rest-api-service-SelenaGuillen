package com.sg.bullsandcowsapi.controllers;

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

    private final Service service;

    public GameController(Service service) {
        this.service = service;
    }

    @GetMapping("/game")
    public List<Game> all() {
        return service.getAll();
    }

    @GetMapping("/rounds/{gameID}")
    public List<Round> allByID(@PathVariable int gameID) {
        return service.getAllByGameID(gameID);
    }


    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game result = service.findGameByID(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game initializeGame(Game game) {
        return service.initializeGame(game);
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeAGuess(@RequestBody Round round) {
        return service.guess(round);
    }

}
