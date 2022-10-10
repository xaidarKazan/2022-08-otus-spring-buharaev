package ru.otus.homeWork.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public long getBooksCount() {
        String query = "select count(*) from BOOK";
        Long count = jdbcTemplate.queryForObject(query, new EmptySqlParameterSource(), Long.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Book book) {
        String query = "insert into BOOK(name, publishingYear, author_id, genre_id) values(:name, :publishingYear, :author_id, :genre_id)";
        Map<String, Object> params = Map.of("name", book.getName(), "publishingYear", book.getPublishingYear(),
                                            "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId());
        jdbcTemplate.update(query, params);
    }

    @Override
    public Book getById(long id) {
        String query = "select b.id, b.name, b.publishingYear, b.author_id, b.genre_id, a.firstName, a.lastName, g.name gName " +
                       "from BOOK b " +
                       "join AUTHOR a on a.id = b.author_id " +
                       "join GENRE g on g.id = b.genre_id " +
                       "where b.id = :id ";
        return jdbcTemplate.queryForObject(query, Map.of("id", id),new BookRowMapper());
    }

    @Override
    public List<Book> getAll() {
        String query = "select b.id, b.name, b.publishingYear, b.author_id, b.genre_id, a.firstName, a.lastName, g.name gName " +
                       "from BOOK b " +
                       "join AUTHOR a on a.id = b.author_id " +
                       "join GENRE g on g.id = b.genre_id " ;
        return jdbcTemplate.query(query, new EmptySqlParameterSource(), new BookRowMapper());
    }

    @Override
    public void deleteById(long id) {
        String query = "delete from BOOK where id = :id";
        jdbcTemplate.update(query, Map.of("id", id));
    }

    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder().id(rs.getLong("id"))
                                 .name(rs.getString("name"))
                                 .publishingYear(rs.getInt("publishingYear"))
                                 .author(Author.builder()
                                               .id(rs.getLong("author_id"))
                                               .firstName(rs.getString("firstName"))
                                               .lastName(rs.getString("lastName"))
                                               .build())
                                 .genre(Genre.builder()
                                             .id(rs.getLong("genre_id"))
                                             .name(rs.getString("gName"))
                                             .build())
                                 .build();
        }
    }
}