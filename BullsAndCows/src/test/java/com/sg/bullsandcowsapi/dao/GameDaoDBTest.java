package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameDaoDBTest {
    @Autowired
    GameDao gameDao;

//    @Autowired
//    Service service;
//
//    @Autowired
//    RoundDao roundDao;

    @BeforeEach
    public void setUp() {
        List<Game> games = gameDao.getAll();
        for (Game game: games) {
            gameDao.deleteGameById(game.getId());
        }
    }
    @Test
    void addGet() {
        Game game = new Game();
        game.setStatus("in progress");
        game.setAnswer("1234");

        gameDao.add(game);

        Game gameFromDao = gameDao.findById(game.getId());
        assertEquals(game, gameFromDao);
    }

    @Test
    void getAll() {
        Game game = new Game();
        game.setAnswer("0987");
        game.setStatus("finished");
        gameDao.add(game);

        Game game2 = new Game();
        game.setAnswer("8900");
        game.setStatus("in progress");
        gameDao.add(game2);

        List<Game> games = gameDao.getAll();

        Assertions.assertEquals(2, games.size());
        Assertions.assertTrue(games.contains(game));
        Assertions.assertTrue(games.contains(game2));
    }

}