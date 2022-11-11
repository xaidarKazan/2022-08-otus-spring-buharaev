package ru.otus.homeWork.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "01", author = "", id = "dropDB", runAlways = true)
    public void dropDB(MongoDatabase db) {
            db.drop();
    }

    @ChangeSet(order = "02", author = "", id = "insertA", runAlways = true)
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> authors = db.getCollection("Author");
        authors.insertMany(Arrays.asList(new Document().append("_id", "1").append("firstName", "Михаил").append("lastName", "Булгаков"),
                                         new Document().append("_id", "2").append("firstName", "Федор").append("lastName", "Достоевский"),
                                         new Document().append("_id", "3").append("firstName", "Антон").append("lastName", "Чехов"),
                                         new Document().append("_id", "4").append("firstName", "Николай").append("lastName", "Гоголь"),
                                         new Document().append("_id", "5").append("firstName", "Александр").append("lastName", "Пушкин"),
                                         new Document().append("_id", "6").append("firstName", "Иван").append("lastName", "Тургенев")));
    }

    @ChangeSet(order = "03", author = "", id = "insertG", runAlways = true)
    public void insertGenres(MongoDatabase db) {
        MongoCollection<Document> genres = db.getCollection("Genre");
        genres.insertMany(Arrays.asList(new Document().append("_id", "1").append("name", "Проза"),
                                        new Document().append("_id", "2").append("name", "Драматургия"),
                                        new Document().append("_id", "3").append("name", "Ужасы")));
    }

    @ChangeSet(order = "04", author = "", id = "insertB", runAlways = true)
    public void insertBooks(MongoDatabase db) {
        MongoCollection<Document> books = db.getCollection("Book"),
                                  authors = db.getCollection("Author"),
                                  genres = db.getCollection("Genre");
        books.insertMany(Arrays.asList(new Document().append("_id", "1")
                                                     .append("name", "Мастер и Маргарита")
                                                     .append("publishingYear", "1928")
                                                     .append("author", getId(authors, "1"))
                                                     .append("genre", getId(genres, "1")),
                                        new Document().append("_id", "2")
                                                      .append("name", "Собачье сердце")
                                                      .append("publishingYear", "1925")
                                                      .append("author", getId(authors, "1"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", "3")
                                                      .append("name", "Преступление и наказание")
                                                      .append("publishingYear", "1866")
                                                      .append("author", getId(authors, "2"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", "4")
                                                      .append("name", "Братья Карамазовы")
                                                      .append("publishingYear", "1879")
                                                      .append("author", getId(authors, "2"))
                                                      .append("genre", getId(genres, "2")),
                                        new Document().append("_id", "5")
                                                      .append("name", "Вишневый сад")
                                                      .append("publishingYear", "1903")
                                                      .append("author", getId(authors, "3"))
                                                      .append("genre", getId(genres, "1")),
                                        new Document().append("_id", "6")
                                                      .append("name", "Мертвые души")
                                                      .append("publishingYear", "1835")
                                                      .append("author", getId(authors, "4"))
                                                      .append("genre", getId(genres, "2")),
                                        new Document().append("_id", "7")
                                                      .append("name", "Вий")
                                                      .append("publishingYear", "1835")
                                                      .append("author", getId(authors, "4"))
                                                      .append("genre", getId(genres, "3")),
                                        new Document().append("_id", "8")
                                                      .append("name", "Пиковая дама")
                                                      .append("publishingYear", "1833")
                                                      .append("author", getId(authors, "5"))
                                                      .append("genre", getId(genres, "3")),
                                        new Document().append("_id", "9")
                                                      .append("name", "Бежин луг")
                                                      .append("publishingYear", "1851")
                                                      .append("author", getId(authors, "6"))
                                                      .append("genre", getId(genres, "3"))));
    }

    @ChangeSet(order = "05", author = "", id = "insertC", runAlways = true)
    public void insertComments(MongoDatabase db) {
        MongoCollection<Document> books = db.getCollection("Book"),
                comments = db.getCollection("Comment");
        comments.insertMany(Arrays.asList(new Document().append("_id", "1")
                                                        .append("content", "Интересная")
                                                        .append("book", getId(books, "1")),
                                          new Document().append("_id", "2")
                                                        .append("content", "Прочитаю еще раз")
                                                        .append("book", getId(books, "1")),
                                          new Document().append("_id", "3")
                                                        .append("content", "Советую, не пожалеете.")
                                                        .append("book", getId(books, "1"))));
    }

    private Object getId(MongoCollection<Document> collection, String idValue) {
        return collection.find(eq("_id", idValue)).first().get("_id");
    }
}