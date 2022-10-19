package ru.otus.homeWork.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public long getAuthorsCount() {
        String query = "select count(*) from AUTHOR";
        Integer count = jdbcTemplate.queryForObject(query, new EmptySqlParameterSource(),  Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Author author) {
        String query = "insert into AUTHOR(firstName, lastName) values(:firstName, :lastName)";
        Map<String, Object> params = Map.of("firstName", author.getFirstName(), "lastName", author.getLastName());
        jdbcTemplate.update(query, params);
    }

    @Override
    public Author getById(long id) {
        String query = "select id, firstName, lastName from AUTHOR where id = :id";
        Map<String, Object> params = Map.of("id", id);
        return jdbcTemplate.queryForObject(query, params, new AuthorRowMapper());
    }

    @Override
    public List<Author> getAll() {
        String query = "select id, firstName, lastName from AUTHOR";
        return jdbcTemplate.query(query, new AuthorRowMapper());
    }

    @Override
    public void deleteById(long id) {
        String query = "delete from AUTHOR where id = :id";
        jdbcTemplate.update(query, Map.of("id", id));
    }

    private class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder().id(rs.getLong("id"))
                                   .firstName(rs.getString("firstName"))
                                   .lastName(rs.getString("lastName"))
                                   .build();
        }
    }
}