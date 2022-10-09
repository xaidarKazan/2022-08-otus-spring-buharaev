package ru.otus.homeWork.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public long getGenresCount() {
        String query = "select count(*) from GENRE";
        Long count = jdbcTemplate.queryForObject(query, new EmptySqlParameterSource(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Genre genre) {
        String query = "insert into GENRE(name) values(:name)";
        jdbcTemplate.update(query, Map.of("name", genre.getName()));
    }

    @Override
    public Genre getById(long id) {
        String query = "select ID, NAME from GENRE where id = :id";
        return jdbcTemplate.queryForObject(query, Map.of("id", id), new GenreRowMapper());
    }

    @Override
    public List<Genre> getAll() {
        String query = "select ID, NAME from GENRE";
        return jdbcTemplate.query(query, new GenreRowMapper());
    }

    @Override
    public void deleteById(long id) {
        String query = "delete from GENRE where id = :id";
        jdbcTemplate.update(query,Map.of("id", id));
    }

    private class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Genre.builder().id(rs.getLong("id"))
                                  .name(rs.getString("name"))
                                  .build();
        }
    }
}