package com.sg.bullsandcowsapi.service;

import com.sg.bullsandcowsapi.dao.GameDao;
import com.sg.bullsandcowsapi.dao.RoundDao;
import com.sg.bullsandcowsapi.models.Game;
import com.sg.bullsandcowsapi.models.Round;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Repository
@Profile("database") //change later b/c not really accurate
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
        newGame.setNumber(generateNumber());
        gameDao.add(newGame);

        return newGame;
    }

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


    @Override
    public Round guess(Round round) {
        round.setResult(getResult(round));
        round.setTimePlayed(Timestamp.valueOf(LocalDateTime.now()));
        round.setGame(gameDao.findById(round.getGameID()));
        return roundDao.add(round);
    }

    //collect result
    private String getResult(Round round) {
        Game game = gameDao.findById(round.getGameID());
        String winningNumber = game.getNumber();
        String playerGuess = round.getPlayerGuess();

        int[] resultArray = findMatches(winningNumber, playerGuess);

        int exactMatches = resultArray[0];
        int partialMatches = resultArray[1];

        //switch status if the entire number matches
        if (exactMatches == 4) {
            game.setStatus("finished");
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


    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public List<Round> getAllByGameID(int gameID) {
        return roundDao.getAllByGameID(gameID);
    }

    @Override
    public Game findGameByID(int id) {
        return gameDao.findById(id);
    }

    @Override
    public List<Round> getById(int gameID) {
        return null;
    }

    //when displaying a game, if a game is still in progress, user should not be able to see the answer
    //ensure that the answer is a 4 digit number with no repeating numbers
    //guesses should only be 4 digit numbers and must be positive



}
