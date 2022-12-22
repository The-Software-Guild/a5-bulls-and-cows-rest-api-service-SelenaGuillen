package com.sg.bullsandcowsapi.service;

import com.sg.bullsandcowsapi.dao.GameDao;
import com.sg.bullsandcowsapi.dao.RoundDao;
import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@Profile({"prod", "test"})
public class ServiceImpl implements Service {

    GameDao gameDao;
    RoundDao roundDao;

    @Autowired
    public ServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game initializeGame(Game newGame) {
        newGame.setStatus("in progress");
        newGame.setAnswer(generateNumber());
        gameDao.add(newGame);

        return newGame;
    }

    @Override
    public Round guess(Round round) {
        Game game = gameDao.findById(round.getGameID());
        round.setTimePlayed(Timestamp.valueOf(LocalDateTime.now()));
        round.setGame(game);
        round.setResult(getResult(round, game));
        gameDao.updateGame(game);
        return roundDao.add(round);
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    @Override
    public List<Round> getAllByGameID(int gameID) {
        for (Round r: roundDao.getAllByGameID(gameID)) {
            r.setGame(gameDao.findById(gameID));
        }
        return roundDao.getAllByGameID(gameID);
    }

    @Override
    public Game findGameByID(int id) {
        return gameDao.findById(id);
    }


    //helper methods
    private String generateNumber() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4) {
            set.add(random.nextInt(10));
        }

        StringBuilder sb = new StringBuilder();
        for(int number: set) {
            sb.append(number);
        }

        return sb.toString();
    }

    private String getResult(Round round, Game game) {
        String winningNumber = game.getAnswer();
        String playerGuess = round.getPlayerGuess();

        int[] resultArray = findMatches(winningNumber, playerGuess);

        int exactMatches = resultArray[0];
        int partialMatches = resultArray[1];

        //switch status if the entire number matches
        if (exactMatches == 4) {
            game.setStatus("finished");
        } else {
            game.setStatus("in progress");
        }
        String result = "e:" + exactMatches + ":" + "p:" + partialMatches;
        return result;
    }


    private int[] findMatches(String number, String guess) {
        int[] result = new int[2];
        int partial = 0;
        int exact = 0;

        if (guess.contentEquals(number)) {
            result[0] = 4;
            return  result;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.indexOf(number.charAt(i)) >= 0 && number.charAt(i) != guess.charAt(i)) {
                partial++;
                result[1]  = partial;
            }
            else if (guess.indexOf(number.charAt(i)) >= 0 && number.charAt(i) == guess.charAt(i)) {
                exact++;
                result[0] = exact;
            }
        }
        return result;
    }
}
