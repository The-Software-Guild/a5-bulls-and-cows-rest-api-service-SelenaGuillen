package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoundDaoDBTest {
    @Autowired
    RoundDao roundDao;

    @Test
    void add() {
        Round round = new Round();
        round.setGameID(1);
        round.setResult("e:4:p0");
        round.setTimePlayed(Timestamp.valueOf(LocalDateTime.now()));

        //incomplete

    }

    @Test
    void getAllByGameID() {
        //
    }
}