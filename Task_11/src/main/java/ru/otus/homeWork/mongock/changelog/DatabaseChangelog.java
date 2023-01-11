package ru.otus.homeWork.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.service.SequenceGeneratorService;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "01", author = "", id = "dropDB", runAlways = true)
    public void dropDB(MongoDatabase db) {
            db.drop();
    }

    @ChangeSet(order = "02", author = "", id = "insertA", runAlways = true)
    public void insertAuthors(MongoDatabase db, SequenceGeneratorService service) {
        MongoCollection<Document> authors = db.getCollection("Author");
        authors.insertMany(Arrays.asList(new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Михаил").append("lastName", "Булгаков"),
                                         new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Федор").append("lastName", "Достоевский"),
                                         new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Антон").append("lastName", "Чехов"),
                                         new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Николай").append("lastName", "Гоголь"),
                                         new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Александр").append("lastName", "Пушкин"),
                                         new Document().append("_id", service.getNextSequence(Author.SEQUENCE_NAME)).append("firstName", "Иван").append("lastName", "Тургенев")));
    }

    @ChangeSet(order = "03", author = "", id = "insertG", runAlways = true)
    public void insertGenres(MongoDatabase db, SequenceGeneratorService service) {
        MongoCollection<Document> genres = db.getCollection("Genre");
        genres.insertMany(Arrays.asList(new Document().append("_id", service.getNextSequence(Genre.SEQUENCE_NAME)).append("name", "Проза"),
                                        new Document().append("_id", service.getNextSequence(Genre.SEQUENCE_NAME)).append("name", "Драматургия"),
                                        new Document().append("_id", service.getNextSequence(Genre.SEQUENCE_NAME)).append("name", "Ужасы")));
    }

    @ChangeSet(order = "04", author = "", id = "insertB", runAlways = true)
    public void insertBooks(MongoDatabase db, SequenceGeneratorService service) {
        MongoCollection<Document> books = db.getCollection("Book"),
                                  authors = db.getCollection("Author"),
                                  genres = db.getCollection("Genre");
        books.insertMany(Arrays.asList(new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                     .append("name", "Мастер и Маргарита")
                                                     .append("publishingYear", "1928")
                                                     .append("author", getId(authors, "1"))
                                                     .append("genre", getId(genres, "1")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Собачье сердце")
                                                      .append("publishingYear", "1925")
                                                      .append("author", getId(authors, "1"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Преступление и наказание")
                                                      .append("publishingYear", "1866")
                                                      .append("author", getId(authors, "2"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Братья Карамазовы")
                                                      .append("publishingYear", "1879")
                                                      .append("author", getId(authors, "2"))
                                                      .append("genre", getId(genres, "2")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Вишневый сад")
                                                      .append("publishingYear", "1903")
                                                      .append("author", getId(authors, "3"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Мертвые души")
                                                      .append("publishingYear", "1835")
                                                      .append("author", getId(authors, "4"))
                                                      .append("genre", getId(genres, "2")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Вий")
                                                      .append("publishingYear", "1835")
                                                      .append("author", getId(authors, "4"))
                                                      .append("genre", getId(genres, "3")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Пиковая дама")
                                                      .append("publishingYear", "1833")
                                                      .append("author", getId(authors, "5"))
                                                      .append("genre", getId(genres, "3")),
                                        new Document().append("_id", service.getNextSequence(Book.SEQUENCE_NAME))
                                                      .append("name", "Бежин луг")
                                                      .append("publishingYear", "1851")
                                                      .append("author", getId(authors, "6"))
                                                      .append("genre", getId(genres, "3"))));
    }

    private Object getId(MongoCollection<Document> collection, String idValue) {
        return collection.find(eq("_id", idValue)).first();
    }
}