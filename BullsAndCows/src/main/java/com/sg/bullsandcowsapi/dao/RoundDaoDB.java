package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile({"prod", "test"})
public class RoundDaoDB implements RoundDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO Round (TimePlayed, PlayerGuess, Result, GameID) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setTimestamp(1, round.getTimePlayed());
            statement.setString(2, round.getPlayerGuess());
            statement.setString(3, round.getResult());
            statement.setInt(4, round.getGameID());
            return statement;
        }, keyHolder);


        round.setId(keyHolder.getKey().intValue());

        return round;
    }


    @Override
    public List<Round> getAllByGameID(int gameID) {
        final String sql = "SELECT * FROM Round WHERE GameID = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameID);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("RoundId"));
            round.setTimePlayed(rs.getTimestamp("TimePlayed"));
            round.setPlayerGuess(rs.getString("PlayerGuess"));
            round.setResult(rs.getString("Result"));
            round.setGameID(rs.getInt("GameID"));
            return round;
        }
    }
}
