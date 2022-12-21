package com.sg.bullsandcowsapi.dao;

import com.sg.bullsandcowsapi.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@Profile("database")
public class GameDaoDB implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO Game (Status, Number) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getStatus());
            statement.setString(2, game.getAnswer());
            return statement;
        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT * FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        final String sql = "SELECT * FROM Game WHERE ID = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public void updateGame(Game game) {
        final String sql = "UPDATE Game SET "
                + "Status = ?, "
                + "Number = ? "
                + "WHERE ID = ?;";
        jdbcTemplate.update(sql, game.getStatus(), game.getAnswer(), game.getId());
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("ID"));
            game.setStatus(rs.getString("Status"));
            game.setAnswer(rs.getString("Number"));
            return game;
        }
    }
}
